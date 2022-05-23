#pragma once

#include <ATMEGA_FreeRTOS.h>
#include <lora_driver.h>
#include <semphr.h>
#include <stdint.h>
#include <stdbool.h>

void downlinkMessageDeconstructor_create(SemaphoreHandle_t mutex);
void downlinkMessageDeconstructor_deconstructDownlinkMessage(lora_driver_payload_t payload);
uint16_t downlinkMessageDeconstructor_getHumidityDataLow();
int16_t downlinkMessageDeconstructor_getTemperatureDataLow();
uint16_t downlinkMessageDeconstructor_getCO2DataLow();
uint16_t downlinkMessageDeconstructor_getHumidityDataHigh();
int16_t downlinkMessageDeconstructor_getTemperatureDataHigh();
uint16_t downlinkMessageDeconstructor_getCO2DataHigh();
uint16_t downlinkMessageDeconstructor_getSoundDataHigh();
