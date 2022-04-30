#include <stdio.h>
#include <avr/io.h>
#include <stdio_driver.h>
#include <serial.h>

#include <ATMEGA_FreeRTOS.h>
#include <task.h>
#include <message_buffer.h>
#include <event_groups.h>

#include <Farmerama.h>
#include <HumidityTemperature.h>
#include <HumidityTemperatureTask.h>
#include <SenderTask.h>

static MessageBufferHandle_t humidityHandle;
static MessageBufferHandle_t temperatureHandle;
static MessageBufferHandle_t senderHandle;

static EventGroupHandle_t actHandle;
static EventGroupHandle_t doneHandle;

static void _initializeDrivers(void) {
	puts("Initializing drivers...");
	humidityTemperature_initializeDriver();
	//lora_driver_initialise(ser_USART1, NULL);
}

static void _createTasks(void) {
	puts("Creating tasks...");
	farmerama_create(senderHandle, humidityHandle, temperatureHandle, actHandle, doneHandle);
	humidityTemperatureTask_create(humidityHandle, temperatureHandle, actHandle, doneHandle);
	senderTask_create(senderHandle);
}

static void _createMessageBuffers(void) {
	puts("Creating message buffers...");
}

static void _createEventGroups(void) {
	puts("Creating event groups...");
	
}

int main(void) {
	stdio_initialise(ser_USART0);
	
	_initializeDrivers();
	_createMessageBuffers();
	_createEventGroups();
	_createTasks();
	
	puts("Starting tasks...");
	vTaskStartScheduler();
}