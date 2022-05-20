#pragma once

#include <FreeRTOS.h>
#include <queue.h>
#include <event_groups.h>


void farmerama_create(QueueHandle_t senderQueue, QueueHandle_t humidityQueue, QueueHandle_t temperatureQueue, QueueHandle_t co2Queue, QueueHandle_t soundQueue, EventGroupHandle_t actEventGroup, EventGroupHandle_t doneEventGroup);
void farmerama_initTask(void* params);
void farmerama_runTask(void);