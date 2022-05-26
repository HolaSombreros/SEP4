package com.dai.dao.humidity;

import com.dai.model.SentMeasurement;
import com.dai.model.SentThresholdLog;
import com.dai.model.ThresholdType;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Future;

public interface HumidityDao {
    Future<SentMeasurement> readLatestByAreaId(int areaId);
    Future<List<SentMeasurement>> readAllByDateAndAreaId(int areaId, LocalDate date);
    Future<List<SentThresholdLog>> getAllExceedingThresholdChanges(int areaId, ThresholdType type, LocalDate date);
}
