package com.dai.dao.humidity;

import com.dai.shared.SentMeasurement;

import java.util.concurrent.Future;

public interface HumidityDao {
    Future<SentMeasurement> getLatestHumidityMeasurement(int areaId);
}
