#pragma once

#include "MeasurementReturnCode.h"

void humidityTemperature_initializeDriver(void);
measurementReturnCode_t humidityTemperature_measure(void);
uint16_t humidityTemperature_getLatestTemperature(void);
uint16_t humidityTemperature_getLatestHumidity(void);