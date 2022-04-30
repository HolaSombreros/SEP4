#pragma once

#include <ATMEGA_FreeRTOS.h>
#include <task.h>
#include <message_buffer.h>
#include <event_groups.h>
#include <TaskReturnCode.h>

taskReturnCode_t humidityTemperatureTask_create(MessageBufferHandle_t humidityHandle, MessageBufferHandle_t temperatureHandle, EventGroupHandle_t actHandle, EventGroupHandle_t doneHandle);
void humidityTemperatureTask_initTask(void* params);
void humidityTemperatureTask_runTask(void);