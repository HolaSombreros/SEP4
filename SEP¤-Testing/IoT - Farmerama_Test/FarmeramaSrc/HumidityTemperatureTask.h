#pragma once

#include <FreeRTOS.h>
#include <task.h>
#include <queue.h>
#include <event_groups.h>

#define BIT_HUMIDITY_ACT 1 << 0
#define BIT_HUMIDITY_DONE 1 << 0
#define BIT_TEMPERATURE_ACT 1 << 1
#define BIT_TEMPERATURE_DONE 1 << 1

void humidityTemperatureTask_create(QueueHandle_t humidityQueue, QueueHandle_t temperatureQueue, EventGroupHandle_t actEventGroup, EventGroupHandle_t doneEventGroup);
void humidityTemperatureTask_initTask(void* params);
void humidityTemperatureTask_runTask(void);