package com.dai.dao.temperature;

import com.dai.repository.TemperatureRepository;
import com.dai.shared.SentMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Repository;

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
}
