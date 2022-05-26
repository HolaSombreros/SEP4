package com.dai.dao.temperature;

import com.dai.model.NotificationLogs;
import com.dai.model.SentThresholdLog;
import com.dai.model.ThresholdType;
import com.dai.repository.TemperatureRepository;
import com.dai.model.SentMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Future;

@Repository
@EnableAsync
public class TemperatureDaoImpl implements TemperatureDao{

    private TemperatureRepository repository;
    @Autowired
    public TemperatureDaoImpl(TemperatureRepository repository) {
        this.repository = repository;
    }

    @Override
    @Async
    public Future<List<SentMeasurement>> readLatestByAreaId(int areaId) {
        return new AsyncResult<>(repository.getLatestByArea(areaId));
    }
    @Override
    public Future<List<SentMeasurement>> readAllByAreaIdAndDate(int areaId, LocalDate date) {
        return new AsyncResult<>(repository.getAllByAreaAndDate(areaId, Date.valueOf(date)));
    }

    @Override
    public Future<List<SentThresholdLog>> getAllExceedingThresholdChanges(int areaId, ThresholdType type, LocalDate date) {
        List<SentThresholdLog> max = repository.getAllExceedingMax(areaId, type.getType(), Date.valueOf(date));
        max.addAll(repository.getAllExceedingMin(areaId, type.getType(), Date.valueOf(date)));
        return new AsyncResult<>(max);
    }

    @Override
    public Future<List<NotificationLogs>> getAllNotificationLogs() {
        List<NotificationLogs> max = repository.getAllMax(ThresholdType.TEMPERATURE.getType());
        max.addAll(repository.getAllMin(ThresholdType.TEMPERATURE.getType()));
        return new AsyncResult<>(max);
    }
}
