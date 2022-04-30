#include <HumidityTemperatureTask.h>
#include <MeasurementReturnCode.h>
#include <HumidityTemperature.h>
#include <stdio.h>

#define TASK_NAME "HumidityTemperatureTask"
#define TASK_PRIORITY 1

static void _run(void* params);

static MessageBufferHandle_t _humidityHandle;
static MessageBufferHandle_t _temperatureHandle;
static EventGroupHandle_t _actHandle;
static EventGroupHandle_t _doneHandle;

void humidityTemperatureTask_create(MessageBufferHandle_t humidityHandle, MessageBufferHandle_t temperatureHandle, EventGroupHandle_t actHandle, EventGroupHandle_t doneHandle) {
	_humidityHandle = humidityHandle;
	_temperatureHandle = temperatureHandle;
	_actHandle = actHandle;
	_doneHandle = doneHandle;
	
	xTaskCreate(_run, TASK_NAME, configMINIMAL_STACK_SIZE, NULL, TASK_PRIORITY, NULL);
}

void humidityTemperatureTask_initTask(void* params) {
	puts("Task started!");
}

void humidityTemperatureTask_runTask() {
	xEventGroupWaitBits(_actHandle, 1, pdTRUE, pdFALSE, pdMS_TO_TICKS(300000UL));
	
	humidityTemperature_measure();
	
	uint16_t humidity = humidityTemperature_getLatestHumidity();
	int16_t temperature = humidityTemperature_getLatestTemperature();
	
	xMessageBufferSend(_humidityHandle, &humidity, sizeof(humidity), pdMS_TO_TICKS(100));
	xMessageBufferSend(_temperatureHandle, &temperature, sizeof(temperature), pdMS_TO_TICKS(100));
	
	xEventGroupSetBits(_doneHandle, 1);
}

static void _run(void* params) {
	humidityTemperatureTask_initTask(params);
	
	while (1) {
		humidityTemperatureTask_runTask();
	}
}

