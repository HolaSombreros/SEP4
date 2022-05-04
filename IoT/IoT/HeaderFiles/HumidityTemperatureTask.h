#pragma once

#include <ATMEGA_FreeRTOS.h>
#include <task.h>
#include <queue.h>
#include <event_groups.h>

void humidityTemperatureTask_create(QueueHandle_t humidityQueue, QueueHandle_t temperatureQueue, EventGroupHandle_t actEventGroup, EventGroupHandle_t doneEventGroup);
void humidityTemperatureTask_initTask(void* params);
void humidityTemperatureTask_runTask(void);