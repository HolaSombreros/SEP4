package com.dai.model.temperature;

import com.dai.shared.SentMeasurement;

public interface TemperatureModel {
    SentMeasurement readLatestTemperature(int areaId) throws Exception;
}
