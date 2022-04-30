#pragma once

#include <MeasurementReturnCode.h>
#include <stdint.h>

measurementReturnCode_t humidityTemperature_initializeDriver(void);
measurementReturnCode_t humidityTemperature_measure(void);
uint16_t humidityTemperature_getLatestHumidity(void);
int16_t humidityTemperature_getLatestTemperature(void);