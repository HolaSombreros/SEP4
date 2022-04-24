#include "SenderTask.h"
#include <lora_driver.h>

#define TASK_NAME "SenderTask"
#define TASK_PRIORITY 0

void _run(void* params);

static lora_driver_payload_t _uplinkPayload;

taskReturnCode_t senderTask_create(void) {
	lora_driver_initialise(ser_USART3, NULL);
	xTaskCreate(_run, TASK_NAME, configMINIMAL_STACK_SIZE, NULL, TASK_PRIORITY, NULL);
	
	return TASK_OK;
}

static void _populateUplinkPayload(void) {
	const uint8_t temperature = humidityTemperature_getLatestTemperature();
	const int8_t humidity = humidityTemperature_getLatestHumidity();
	
	// Populate the private uplink payload variable...
}

static void _run(void* params) {
	lora_driver_resetRn2483(1);
	vTaskDelay(2);
	lora_driver_resetRn2483(0);
	vTaskDelay(150);
	lora_driver_flushBuffers();
	
	_uplinkPayload.len = 20;
	_uplinkPayload.portNo = 1;
	
	TickType_t lastWakeTime = xTaskGetTickCount();
	const TickType_t frequency = pdMS_TO_TICKS(300000UL);
	
	while (1) {
		xTaskDelayUntil(&lastWakeTime, frequency);
		_populateUplinkPayload();
		lora_driver_sendUploadMessage(true, &_uplinkPayload);
	}
}

