package com.dai.model.temperature;

import com.dai.shared.SentMeasurement;

import java.util.List;

public interface TemperatureModel {
    List<SentMeasurement> readLatestTemperature(int areaId) throws Exception;
}
