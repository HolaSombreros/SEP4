#pragma once

#include <ATMEGA_FreeRTOS.h>
#include <lora_driver.h>
#include <message_buffer.h>
#include <event_groups.h>

void farmerama_create(MessageBufferHandle_t senderBuffer, MessageBufferHandle_t humidityBuffer, MessageBufferHandle_t temperatureBuffer, EventGroupHandle_t actHandle, EventGroupHandle_t doneHandle);
void farmerama_initTask(void* params);
void farmerama_runTask(void);