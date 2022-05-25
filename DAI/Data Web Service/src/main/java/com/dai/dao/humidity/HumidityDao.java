package com.dai.dao.humidity;

import com.dai.model.SentMeasurement;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Future;

public interface HumidityDao {
    Future<SentMeasurement> readLatestByAreaId(int areaId);
    Future<List<SentMeasurement>> readAllByDateAndAreaId(int areaId, LocalDate date);
}
