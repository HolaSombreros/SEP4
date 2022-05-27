#pragma once

#include <ATMEGA_FreeRTOS.h>
#include <queue.h>
#include <event_groups.h>

#define BIT_SOUND_ACT 1 << 3
#define BIT_SOUND_DONE 1 << 3

void soundTask_create(QueueHandle_t soundQueue, EventGroupHandle_t actEventGroup, EventGroupHandle_t doneEventGroup);
void soundTask_initTask(void* params);
void soundTask_runTask(void);