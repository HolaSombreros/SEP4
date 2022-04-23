package com.dai.dao.temperature;

import com.dai.shared.Area;
import com.dai.shared.Barn;
import com.dai.shared.Measurement;
import com.dai.shared.SentMeasurement;

import java.util.concurrent.Future;

public interface TemperatureDao {
    Future<SentMeasurement> getLatestTemperatureMeasurement(int areaId);
}
