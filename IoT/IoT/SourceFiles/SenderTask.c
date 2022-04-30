#include <SenderTask.h>
#include <HumidityTemperature.h>
#include <stdio.h>

#define TASK_NAME "SenderTask"
#define TASK_PRIORITY 0

static void _run(void* params);
static MessageBufferHandle_t _senderHandle;


taskReturnCode_t senderTask_create(MessageBufferHandle_t senderHandle) {
	_senderHandle = senderHandle;
	
	lora_driver_initialise(ser_USART3, NULL);
	xTaskCreate(_run, TASK_NAME, configMINIMAL_STACK_SIZE, NULL, TASK_PRIORITY, NULL);
	
	return TASK_OK;
}

void senderTask_initTask(void* params) {
	lora_driver_resetRn2483(1);
	vTaskDelay(2);
	lora_driver_resetRn2483(0);
	vTaskDelay(150);
	lora_driver_flushBuffers();
}

void senderTask_runTask() {
	// TODO - EventGroup or not??
	
	lora_driver_payload_t uplinkPayload;
	xMessageBufferReceive(_senderHandle, &uplinkPayload, sizeof(uplinkPayload), pdMS_TO_TICKS(100));
	xMessageBufferReset(_senderHandle);
	
	
	/*
	lora_driver_returnCode_t returnCode;
	if ((returnCode = lora_driver_sendUploadMessage(false, &uplinkPayload)) == LORA_MAC_TX_OK) {
		
	} else if (returnCode == LORA_MAC_RX) {
		
	}
	*/
	
	printf("Humidity: %d | Temperature: %d\n", uplinkPayload.bytes[1], uplinkPayload.bytes[3]);
}

static void _run(void* params) {
	senderTask_initTask(params);
	
	while (1) {
		senderTask_runTask();
	}
}
