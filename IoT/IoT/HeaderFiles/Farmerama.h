#pragma once

#include <ATMEGA_FreeRTOS.h>
#include <queue.h>
#include <event_groups.h>

void farmerama_create(QueueHandle_t senderQueue, QueueHandle_t humidityQueue, QueueHandle_t temperatureQueue, EventGroupHandle_t actEventGroup, EventGroupHandle_t doneEventGroup);
void farmerama_initTask(void* params);
void farmerama_runTask(void);