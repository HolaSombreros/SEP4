package com.dai.dao.temperature;

import com.dai.helpers.Helper;
import com.dai.repository.TemperatureRepository;
import com.dai.shared.SentMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Repository;

import java.sql.Date;
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
    public Future<SentMeasurement> getLatestTemperatureMeasurement(int areaId) {
        return new AsyncResult<>(repository.findFirstTemperatureAndMeasuredDateByAreaIdOrderByIdDesc(areaId));
    }

    @Override
    public Future<List<SentMeasurement>> readAllTemperatures(int areaId) {
        return getAreaTemperaturesInDate(areaId, LocalDate.now());
    }

    @Override
    public Future<List<SentMeasurement>> getAreaTemperaturesInDate(int areaId, LocalDate date) {
        return new AsyncResult<>(repository.getAreaTemperaturesInDate(areaId, Date.valueOf(date)));
    }
}
