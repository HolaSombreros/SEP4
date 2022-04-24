#include "HumidityTemperatureTask.h"

#define TASK_NAME "HumidityTemperatureTask"
#define TASK_PRIORITY 1

void _run(void* params);

taskReturnCode_t humidityTemperatureTask_create(void) {
	if (humidityTemperature_initializeDriver() == MEASUREMENT_OK) {
		xTaskCreate(_run, TASK_NAME, configMINIMAL_STACK_SIZE, NULL, TASK_PRIORITY, NULL);
		
		return TASK_OK;
	} else {
		return TASK_FAILED;
	}
}

static void _run(void* params) {
	TickType_t lastWakeTime = xTaskGetTickCount();
	const TickType_t frequency = pdMS_TO_TICKS(300000UL);
	
	while (1) {
		xTaskDelayUntil(&lastWakeTime, frequency);
		if (humidityTemperature_measure() != MEASUREMENT_OK) {
			// Oh no...
		} else {
			// Oh yes!
		}
	}
}

