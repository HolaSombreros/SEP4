package com.dai.dao.temperature;

import com.dai.shared.SentMeasurement;

import java.util.concurrent.Future;

public interface TemperatureDao {
    Future<SentMeasurement> getLatestTemperatureMeasurement(int areaId);
}
