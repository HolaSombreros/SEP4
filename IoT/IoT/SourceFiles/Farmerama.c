#include <Farmerama.h>
#include <UplinkMessageBuilder.h>
#include <stdio.h>

#define TASK_NAME "FarmeramaTask"
#define TASK_PRIORITY 1
#define TASK_INTERVAL 300000UL
#define PORT 1

static void _run(void* params);

static MessageBufferHandle_t _senderBuffer;
static MessageBufferHandle_t _humidityBuffer;
static MessageBufferHandle_t _temperatureBuffer;
static EventGroupHandle_t _actHandle;
static EventGroupHandle_t _doneHandle;

void farmerama_create(MessageBufferHandle_t senderBuffer, MessageBufferHandle_t humidityBuffer, MessageBufferHandle_t temperatureBuffer, EventGroupHandle_t actHandle, EventGroupHandle_t doneHandle) {
	_senderBuffer = senderBuffer;
	_humidityBuffer = humidityBuffer;
	_temperatureBuffer = temperatureBuffer;
	_actHandle = actHandle;
	_doneHandle = doneHandle;
	
	xTaskCreate(_run, TASK_NAME, configMINIMAL_STACK_SIZE, NULL, TASK_PRIORITY, NULL);
}

void farmerama_initTask(void* params) {
	
}

void farmerama_runTask(void) {
	xEventGroupSetBits(_actHandle, 1);
	xEventGroupWaitBits(_doneHandle, 1, pdTRUE, pdTRUE, pdMS_TO_TICKS(300000UL));
	
	uint16_t humidity;
	if (xMessageBufferIsEmpty(_humidityBuffer) == pdFALSE) {
		xMessageBufferReceive(_humidityBuffer, &humidity, sizeof(humidity), pdMS_TO_TICKS(100));
	}
	
	int16_t temperature;
	if (xMessageBufferIsEmpty(_temperatureBuffer) == pdFALSE) {
		xMessageBufferReceive(_temperatureBuffer, &temperature, sizeof(temperature), pdMS_TO_TICKS(100));
	}
	
	xMessageBufferReset(_humidityBuffer);
	xMessageBufferReset(_temperatureBuffer);
	
	printf("Humidity before builder: %d\n", humidity);
	
	uplinkMessageBuilder_setHumidityData(humidity);
	uplinkMessageBuilder_setTemperatureData(temperature);
	
	lora_driver_payload_t message;
	message = uplinkMessageBuilder_buildUplinkMessage(PORT);
	xMessageBufferSend(_senderBuffer, &message, sizeof(message), pdMS_TO_TICKS(100));
	
	TickType_t lastWakeTime = xTaskGetTickCount();
	const TickType_t frequency = pdMS_TO_TICKS(TASK_INTERVAL);
	xTaskDelayUntil(&lastWakeTime, frequency);
}

static void _run(void* params) {
	farmerama_initTask(params);
	
	while (1) {
		farmerama_runTask();
	}
}