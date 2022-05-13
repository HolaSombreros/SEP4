#include <CO2Task.h>
#include <HumidityTemperatureTask.h>
#include <stdio.h>
#include <stdint.h>
#include <mh_z19.h>

#define TASK_NAME "CO2Task"
#define TASK_PRIORITY configMAX_PRIORITIES - 2

static void _co2CallBack(uint16_t ppm);

static void _run(void* params);

static QueueHandle_t _co2Queue;
static EventGroupHandle_t _actEventGroup;
static EventGroupHandle_t _doneEventGroup;

static uint16_t ppm;

void co2Task_create(QueueHandle_t co2Queue, EventGroupHandle_t actEventGroup, EventGroupHandle_t doneEventGroup) {
	_co2Queue = co2Queue;
	_actEventGroup = actEventGroup;
	_doneEventGroup = doneEventGroup;
	
	xTaskCreate(_run, TASK_NAME, configMINIMAL_STACK_SIZE, NULL, TASK_PRIORITY, NULL);
}

void co2Task_initTask(void* params) {
	mh_z19_injectCallBack(_co2CallBack);
}

void co2Task_runTask() {
	xEventGroupWaitBits(_doneEventGroup,
	BIT_HUMIDITY_DONE | BIT_TEMPERATURE_DONE,
	pdFALSE, pdTRUE, portMAX_DELAY
	);
	
	if((mh_z19_takeMeassuring()) != MHZ19_OK){
		ppm = 0;
		_co2CallBack(ppm);
	}
		
	xEventGroupSetBits(_doneEventGroup,
	BIT_CO2_DONE
	);
}

static void _co2CallBack(uint16_t ppm){
	xQueueSendToBack(_co2Queue, &ppm, portMAX_DELAY);
}

static void _run(void* params) {
	co2Task_initTask(params);
	
	while (1) {
		co2Task_runTask();
	}
}

