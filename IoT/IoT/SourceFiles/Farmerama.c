#include <Farmerama.h>
#include <CO2Task.h>
#include <SoundTask.h>
#include <HumidityTemperatureTask.h>
#include <UplinkMessageBuilder.h>
#include <stdio.h>
#include <lora_driver.h>
#include <task.h>

#define TASK_NAME "FarmeramaTask"
#define TASK_INTERVAL 3000UL // Default value = 300000UL (5 minutes)
#define TASK_PRIORITY configMAX_PRIORITIES - 1
#define PORT 1

static void _run(void* params);

static QueueHandle_t _senderQueue;
static QueueHandle_t _humidityQueue;
static QueueHandle_t _temperatureQueue;
static QueueHandle_t _co2Queue;
static QueueHandle_t _soundQueue;
static QueueHandle_t _servoQueue;
static EventGroupHandle_t _actEventGroup;
static EventGroupHandle_t _doneEventGroup;

void farmerama_create(QueueHandle_t senderQueue, QueueHandle_t humidityQueue, QueueHandle_t temperatureQueue, QueueHandle_t co2Queue, QueueHandle_t soundQueue, QueueHandle_t servoQueue, EventGroupHandle_t actEventGroup, EventGroupHandle_t doneEventGroup) {
	_senderQueue = senderQueue;
	_humidityQueue = humidityQueue;
	_temperatureQueue = temperatureQueue;
	_co2Queue = co2Queue;
	_soundQueue = soundQueue;
	_servoQueue = servoQueue;
	_actEventGroup = actEventGroup;
	_doneEventGroup = doneEventGroup;
	
	xTaskCreate(_run, TASK_NAME, configMINIMAL_STACK_SIZE, NULL, TASK_PRIORITY, NULL);
}

void farmerama_initTask(void* params) {
	
}

void farmerama_runTask(void) {	
	xEventGroupSetBits(_actEventGroup, 
		BIT_HUMIDITY_ACT | BIT_TEMPERATURE_ACT
	);
	
	xEventGroupWaitBits(_doneEventGroup, 
		BIT_HUMIDITY_DONE | BIT_TEMPERATURE_DONE | BIT_CO2_DONE | BIT_SOUND_DONE,
		pdTRUE, pdTRUE, pdMS_TO_TICKS(TASK_INTERVAL)
	);
	
	uint16_t humidity;
	int16_t temperature;
	uint16_t ppm;
	uint16_t sound;
	
	xQueueReceive(_humidityQueue, &humidity, pdMS_TO_TICKS(10000));
	xQueueReceive(_temperatureQueue, &temperature, pdMS_TO_TICKS(10000));
	xQueueReceive(_co2Queue, &ppm, pdMS_TO_TICKS(10000));
	xQueueReceive(_soundQueue, &sound, pdMS_TO_TICKS(10000));
	
	xQueueSendToBack(_servoQueue, &humidity, pdMS_TO_TICKS(10000));
	xQueueSendToBack(_servoQueue, &temperature, pdMS_TO_TICKS(10000));
	xQueueSendToBack(_servoQueue, &ppm, pdMS_TO_TICKS(10000));
	xQueueSendToBack(_servoQueue, &sound, pdMS_TO_TICKS(10000));
	
	uplinkMessageBuilder_setHumidityData(humidity);
	uplinkMessageBuilder_setTemperatureData(temperature);
	uplinkMessageBuilder_setCO2Data(ppm);
	uplinkMessageBuilder_setSoundData(sound);
	
	lora_driver_payload_t message = uplinkMessageBuilder_buildUplinkMessage(PORT);
	xQueueSendToBack(_senderQueue, &message, pdMS_TO_TICKS(1000));
	
	TickType_t lastWakeTime = xTaskGetTickCount();
	xTaskDelayUntil(&lastWakeTime, pdMS_TO_TICKS(TASK_INTERVAL));
}

static void _run(void* params) {
	farmerama_initTask(params);
	
	while (1) {
		farmerama_runTask();
	}
}