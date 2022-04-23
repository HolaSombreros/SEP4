package com.dai.model.temperature;

import com.dai.shared.SentMeasurement;

import java.util.concurrent.Future;

public class TemperatureModelImpl implements TemperatureModel{
    @Override
    public Future<SentMeasurement> readLatestTemperature() {
        return null;
    }
}
