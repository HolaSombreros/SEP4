package com.dai.dao.humidity;

import com.dai.shared.SentMeasurement;

import java.sql.Date;
import java.util.List;
import java.util.concurrent.Future;

public interface HumidityDao {
    Future<SentMeasurement> getLatestHumidityMeasurement(int areaId);
    Future<List<SentMeasurement>> getHumidityMeasurementsByDate(int areaId, Date date);
}
