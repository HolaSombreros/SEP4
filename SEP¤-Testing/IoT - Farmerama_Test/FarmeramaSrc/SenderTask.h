#pragma once

#include <ATMEGA_FreeRTOS.h>
#include <task.h>
#include <queue.h>

void senderTask_create(QueueHandle_t senderQueue);
void senderTask_initTask(void* params);
void senderTask_runTask(void);