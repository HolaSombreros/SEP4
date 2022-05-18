#pragma once

#include <FreeRTOS.h>
#include <task.h>
#include <queue.h>
#include <event_groups.h>

#define BIT_CO2_ACT 1 << 2
#define BIT_CO2_DONE 1 << 2

void co2Task_create(QueueHandle_t co2Queue, EventGroupHandle_t actEventGroup, EventGroupHandle_t doneEventGroup);
void co2Task_initTask(void* params);
void co2Task_runTask(void);
