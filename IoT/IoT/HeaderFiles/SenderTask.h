#pragma once

#include <TaskReturnCode.h>

#include <ATMEGA_FreeRTOS.h>
#include <task.h>
#include <lora_driver.h>
#include <stdint.h>
#include <message_buffer.h>
#include <event_groups.h>

taskReturnCode_t senderTask_create(MessageBufferHandle_t senderHandle);
void senderTask_initTask(void* params);
void senderTask_runTask(void);