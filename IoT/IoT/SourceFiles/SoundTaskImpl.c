#include <SoundTask.h>
#include <CO2Task.h>
#include <stdio.h>
#include <stdint.h>
#include <sen14262.h>

#define TASK_NAME "SoundTask"
#define TASK_PRIORITY configMAX_PRIORITIES - 2

static void _run(void* params);

static QueueHandle_t _soundQueue;
static EventGroupHandle_t _actEventGroup;
static EventGroupHandle_t _doneEventGroup;

void soundTask_create(QueueHandle_t soundQueue, EventGroupHandle_t actEventGroup, EventGroupHandle_t doneEventGroup) {
	_soundQueue = soundQueue;
	_actEventGroup = actEventGroup;
	_doneEventGroup = doneEventGroup;
	
	xTaskCreate(_run, TASK_NAME, configMINIMAL_STACK_SIZE, NULL, TASK_PRIORITY, NULL);
}

void soundTask_initTask(void* params) {
	
}

void soundTask_runTask() {
	xEventGroupWaitBits(_doneEventGroup, BIT_CO2_DONE, pdFALSE, pdTRUE, portMAX_DELAY);
	
	uint16_t sound = sen14262_envelope();
	
	xQueueSendToBack(_soundQueue, &sound, portMAX_DELAY);
	
	xEventGroupSetBits(_doneEventGroup, BIT_SOUND_DONE);
}

static void _run(void* params) {
	soundTask_initTask(params);
	
	while (1) {
		soundTask_runTask();
	}
}

