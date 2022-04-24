#include <stdio.h>
#include <avr/io.h>

#include <ATMEGA_FreeRTOS.h>
#include <task.h>

#include <stdio_driver.h>
#include <serial.h>

#include <status_leds.h>

// Drivers
#include <mh_z19.h>
#include <hih8120.h>


/*-----------------------------------------------------------*/
static void _initialize(void) {
	puts("Starting...");
	
	// Make it possible to use stdio on COM port 0 (USB) on Arduino board - Setting 57600,8,N,1
	stdio_initialise(ser_USART0);
	
	status_leds_initialise(5); // what is this?
}

static void _createTasks(void) {
	puts("Creating tasks...");
	humidityTemperatureTask_create();
	senderTask_create();
}

/*-----------------------------------------------------------*/
int main(void) {
	_initialize();
	
	_createTasks(); // Must be done as the very first thing!!
	
	puts("Starting tasks...");
	vTaskStartScheduler();
}