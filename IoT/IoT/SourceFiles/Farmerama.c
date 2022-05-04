#include <Farmerama.h>
#include <UplinkMessageBuilder.h>
#include <stdio.h>
#include <lora_driver.h>

#define TASK_NAME "FarmeramaTask"
#define TASK_INTERVAL 300000UL // Default value = 300000UL (5 minutes)
#define TASK_PRIORITY configMAX_PRIORITIES - 1
#define PORT 1

#define BIT_HUMIDITY_ACT 1 << 0
#define BIT_HUMIDITY_DONE 1 << 0
#define BIT_TEMPERATURE_ACT 1 << 1
#define BIT_TEMPERATURE_DONE 1 << 1

static void _run(void* params);

static QueueHandle_t _senderQueue;
static QueueHandle_t _humidityQueue;
static QueueHandle_t _temperatureQueue;
static EventGroupHandle_t _actEventGroup;
static EventGroupHandle_t _doneEventGroup;

void farmerama_create(QueueHandle_t senderQueue, QueueHandle_t humidityQueue, QueueHandle_t temperatureQueue, EventGroupHandle_t actEventGroup, EventGroupHandle_t doneEventGroup) {
	_senderQueue = senderQueue;
	_humidityQueue = humidityQueue;
	_temperatureQueue = temperatureQueue;
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
		BIT_HUMIDITY_DONE | BIT_TEMPERATURE_DONE, 
		pdTRUE, pdTRUE, pdMS_TO_TICKS(TASK_INTERVAL)
	);
	
	uint16_t humidity;
	int16_t temperature;
	
	xQueueReceive(_humidityQueue, &humidity, pdMS_TO_TICKS(100));
	xQueueReceive(_temperatureQueue, &temperature, pdMS_TO_TICKS(100));
	
	uplinkMessageBuilder_setHumidityData(humidity);
	uplinkMessageBuilder_setTemperatureData(temperature);
	
	lora_driver_payload_t message = uplinkMessageBuilder_buildUplinkMessage(PORT);
	xQueueSendToBack(_senderQueue, &message, pdMS_TO_TICKS(100));
	
	TickType_t lastWakeTime = xTaskGetTickCount();
	xTaskDelayUntil(&lastWakeTime, pdMS_TO_TICKS(TASK_INTERVAL));
}

static void _run(void* params) {
	farmerama_initTask(params);
	
	while (1) {
		farmerama_runTask();
	}
}