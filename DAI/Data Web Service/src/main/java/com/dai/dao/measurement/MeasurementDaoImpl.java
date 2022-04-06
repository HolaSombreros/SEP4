package com.dai.dao.measurement;

import com.dai.shared.Area;
import com.dai.shared.Hardware;
import com.dai.shared.Measurement;
import com.dai.repository.MeasurementRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.concurrent.Future;

@Repository
@EnableAsync
public class MeasurementDaoImpl implements MeasurementDao{

    private MeasurementRepository repository;

    public MeasurementDaoImpl(MeasurementRepository repository) {
        this.repository = repository;
    }

    @Override
    @Async
    public Future<Measurement> create(LocalDateTime measuredDate, double temperature, double humidity, int co2, double sound, Hardware hardware, Area area) {
        return new AsyncResult<>(repository.save(new Measurement(measuredDate, temperature,humidity, co2, sound, hardware, area)));
    }

    @Override
    public Future<Measurement> read(int id) {
        return null;
    }

    @Override
    public Future<Measurement> update(Measurement measurement) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    @Async
    public Future<Measurement> getLatestMeasurement() {
        return new AsyncResult<>(repository.getFirstByOrderByIdDesc());
    }
}
