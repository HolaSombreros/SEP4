package com.dai.dao.measurement;

import com.dai.repository.MeasurementRepository;
import com.dai.shared.Measurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Repository;
import java.util.concurrent.Future;

@Repository
@EnableAsync
public class MeasurementDaoImpl implements MeasurementDao {

    private MeasurementRepository repository;
    @Autowired
    public MeasurementDaoImpl(MeasurementRepository repository) {
        this.repository = repository;
    }

    @Override
    public Future<Measurement> saveMeasurement(Measurement measurement) {
        return new AsyncResult<>(repository.save(measurement));
    }
}
