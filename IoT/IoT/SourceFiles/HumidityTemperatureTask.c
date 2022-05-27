#include <HumidityTemperatureTask.h>
#include <stdint.h>
#include <task.h>
#include <hih8120.h>
#include <Configuration.h>

#define TASK_NAME "HumidityTemperatureTask"
#define TASK_PRIORITY configMAX_PRIORITIES - 2

static void _run(void* params);

static QueueHandle_t _humidityQueue;
static QueueHandle_t _temperatureQueue;
static EventGroupHandle_t _actEventGroup;
static EventGroupHandle_t _doneEventGroup;

static uint16_t _latestHumidity;
static int16_t _latestTemperature;

void humidityTemperatureTask_create(QueueHandle_t humidityQueue, 
									QueueHandle_t temperatureQueue, 
									EventGroupHandle_t actEventGroup, 
									EventGroupHandle_t doneEventGroup) {
	_humidityQueue = humidityQueue;
	_temperatureQueue = temperatureQueue;
	_actEventGroup = actEventGroup;
	_doneEventGroup = doneEventGroup;
	
	_latestHumidity = 0;
	_latestTemperature = 0;
	
	xTaskCreate(_run, 
				TASK_NAME, 
				configMINIMAL_STACK_SIZE, 
				NULL, 
				TASK_PRIORITY, 
				NULL
	);
}

void humidityTemperatureTask_initTask(void* params) {
	
}

void humidityTemperatureTask_runTask() {
	xEventGroupWaitBits(_actEventGroup, 
					    BIT_HUMIDITY_ACT | BIT_TEMPERATURE_ACT,
						pdTRUE,	
						pdFALSE, 
						portMAX_DELAY
	);
	
	if (hih8120_wakeup() == HIH8120_OK) {
		vTaskDelay(pdMS_TO_TICKS(100));
		
		if (hih8120_measure() == HIH8120_OK) {
			vTaskDelay(pdMS_TO_TICKS(50));
			_latestHumidity = hih8120_getHumidityPercent_x10();
			_latestTemperature = hih8120_getTemperature_x10();
		} else {
			_latestHumidity = CONFIG_INVALID_HUMIDITY_VALUE;
			_latestTemperature = CONFIG_INVALID_TEMPERATURE_VALUE;
		}
	} else {
		_latestHumidity = CONFIG_INVALID_HUMIDITY_VALUE;
		_latestTemperature = CONFIG_INVALID_TEMPERATURE_VALUE;
	}
	
	xQueueSendToBack(_humidityQueue, &_latestHumidity, portMAX_DELAY);
	xQueueSendToBack(_temperatureQueue, &_latestTemperature, portMAX_DELAY);
	xEventGroupSetBits(_doneEventGroup, BIT_HUMIDITY_DONE | BIT_TEMPERATURE_DONE);
}

static void _run(void* params) {
	humidityTemperatureTask_initTask(params);
	
	while (1) {
		humidityTemperatureTask_runTask();
	}
}

