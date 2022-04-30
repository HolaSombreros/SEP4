#include <HumidityTemperature.h>
#include <ATMEGA_FreeRTOS.h>
#include <task.h>
#include <hih8120.h>
#include <stdio.h>

measurementReturnCode_t humidityTemperature_initializeDriver(void) {
	if (hih8120_initialise() == HIH8120_OK) {
		//puts("Humidity/Temperature driver initialized!");
		return MEASUREMENT_OK;
	} else {
		puts("Failed to initialize driver...");
		return MEASUREMENT_FAILED;
	}
}

measurementReturnCode_t humidityTemperature_measure(void) {
	if (hih8120_wakeup() == HIH8120_OK) {
		vTaskDelay(100);
		
		if (hih8120_measure() == HIH8120_OK) {
			vTaskDelay(2);
			return MEASUREMENT_OK;
		} else {
			puts("Failed to measure...");
			return MEASUREMENT_FAILED;
		}
	} else {
		puts("Failed to wake up driver...");
		return MEASUREMENT_FAILED;
	}
}

uint16_t humidityTemperature_getLatestHumidity(void) {
	return hih8120_getHumidityPercent_x10();
}

int16_t humidityTemperature_getLatestTemperature(void) {
	return hih8120_getTemperature_x10();
}