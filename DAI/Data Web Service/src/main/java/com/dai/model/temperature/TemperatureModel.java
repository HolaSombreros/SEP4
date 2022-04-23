package com.dai.model.temperature;

import com.dai.shared.SentMeasurement;

import java.util.concurrent.Future;

public interface TemperatureModel {
    SentMeasurement readLatestTemperature() throws Exception;
}
