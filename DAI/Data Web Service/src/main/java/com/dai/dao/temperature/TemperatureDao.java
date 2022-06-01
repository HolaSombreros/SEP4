package com.dai.dao.temperature;

import com.dai.model.NotificationLogs;
import com.dai.model.SentMeasurement;
import com.dai.model.SentThresholdLog;
import com.dai.model.ThresholdType;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Future;

public interface TemperatureDao {
    Future<List<SentMeasurement>> readLatestByAreaId(int areaId);
    Future<List<SentMeasurement>> readAllByAreaIdAndDate(int areaId, LocalDate date);
    Future<List<SentThresholdLog>> getAllExceedingThresholdChanges(int areaId, ThresholdType type, LocalDate date);

    Future<List<NotificationLogs>> getAllNotificationLogs();
}
