#include <stdio.h>
#include <avr/io.h>
#include <stdio_driver.h>
#include <serial.h>

#include <ATMEGA_FreeRTOS.h>
#include <task.h>
#include <queue.h>
#include <event_groups.h>
#include <hih8120.h>
#include <mh_z19.h>
#include <lora_driver.h>

#include <Farmerama.h>
#include <HumidityTemperatureTask.h>
#include <CO2Task.h>
#include <SenderTask.h>

static QueueHandle_t _humidityQueue;
static QueueHandle_t _temperatureQueue;
static QueueHandle_t _co2Queue;
static QueueHandle_t _senderQueue;

static EventGroupHandle_t _actEventGroup;
static EventGroupHandle_t _doneEventGroup;

static void _initDrivers(void) {
	puts("Initializing drivers...");
	hih8120_initialise();
	mh_z19_initialise(ser_USART3);
	lora_driver_initialise(ser_USART1, NULL);
}

static void _createTasks(void) {
	farmerama_create(_senderQueue, _humidityQueue, _temperatureQueue, _co2Queue, _actEventGroup, _doneEventGroup);
	humidityTemperatureTask_create(_humidityQueue, _temperatureQueue, _actEventGroup, _doneEventGroup);
	co2Task_create(_co2Queue, _actEventGroup, _doneEventGroup);
	senderTask_create(_senderQueue);
}

static void _createQueues(void) {	
	_humidityQueue = xQueueCreate(10, sizeof(uint16_t));
	_temperatureQueue = xQueueCreate(10, sizeof(int16_t));
	_co2Queue = xQueueCreate(10, sizeof(uint16_t));
	_senderQueue = xQueueCreate(10, sizeof(lora_driver_payload_t));
}

static void _createEventGroups(void) {
	_actEventGroup = xEventGroupCreate();
	_doneEventGroup = xEventGroupCreate();
}

int main(void) {
	stdio_initialise(ser_USART0);
	
	_initDrivers();
	_createQueues();
	_createEventGroups();
	_createTasks();
	
	puts("Starting...");
	vTaskStartScheduler();
}