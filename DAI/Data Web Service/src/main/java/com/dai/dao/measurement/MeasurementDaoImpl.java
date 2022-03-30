package com.dai.dao.measurement;

import com.dai.shared.Hardware;
import com.dai.shared.Measurement;
import com.dai.shared.MeasurementType;
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
    public Future<Measurement> create(MeasurementType type, double value, LocalDateTime measuredDate, Hardware hardware) {
        return new AsyncResult<>(repository.save(new Measurement(type, value, measuredDate, hardware)));
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
    public Future<Measurement> getLatestByType(MeasurementType type) {
        return new AsyncResult<>(repository.getLatestByType(type));
    }
}
