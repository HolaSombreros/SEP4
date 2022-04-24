#include "HumidityTemperature.h"
#include <hih8120.h>

measurementReturnCode_t humidityTemperature_initializeDriver(void) {
	if (hih8120_initialise()) {
		return MEASUREMENT_OK;
	} else {
		return MEASUREMENT_FAILED;
	}
}

measurementReturnCode_t humidityTemperature_measure(void) {
	if (hih8120_wakeup() == HIH8120_OK) {
		vTaskDelay(100);
		
		if (hih8120_measure() == HIH8120_OK) {
			return MEASUREMENT_OK;
		} else {
			// ?
		}
	} else {
		// ?
	}
}

int8_t humidityTemperature_getLatestHumidity(void) {
	return hih8120_getHumidityPercent_x10();
}

uint8_t humidityTemperature_getLatestTemperature(void) {
	return hih8120_getTemperature_x10();
}

