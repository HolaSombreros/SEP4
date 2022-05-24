#pragma once

#include <ATMEGA_FreeRTOS.h>
#include <task.h>
#include <queue.h>

void servoTask_create(QueueHandle_t servoQueue);
void servoTask_initTask(void* params);
void servoTask_runTask(void);
