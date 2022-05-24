#include <ReceiverTask.h>
#include <task.h>
#include <message_buffer.h>
#include <stdint.h>
#include <stdio.h>
#include <stddef.h>
#include <lora_driver.h>
#include <DownlinkMessageDeconstructor.h>

#define TASK_NAME "ReceiverTask"
#define TASK_PRIORITY configMAX_PRIORITIES - 2

static MessageBufferHandle_t _receiverBuffer;

static void _run(void* params);

void receiverTask_create(MessageBufferHandle_t receiverBuffer){
	_receiverBuffer = receiverBuffer;
	
	xTaskCreate(_run, TASK_NAME, configMINIMAL_STACK_SIZE, NULL, TASK_PRIORITY, NULL);
}

void receiverTask_initTask(void* params){
	
}

void receiverTask_runTask(void){
	lora_driver_payload_t payload;
	// TODO - take most recent payload and clear all old ones?
	xMessageBufferReceive(_receiverBuffer, &payload, sizeof(lora_driver_payload_t), portMAX_DELAY);
	if(payload.len == 15){
		downlinkMessageDeconstructor_deconstructDownlinkMessage(payload);
	}
}

static void _run(void* params) {
	receiverTask_initTask(params);
	
	while (1) {
		receiverTask_runTask();
	}
}