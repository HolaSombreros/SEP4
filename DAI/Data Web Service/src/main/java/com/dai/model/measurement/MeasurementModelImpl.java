package com.dai.model.measurement;

import com.dai.dao.measurement.MeasurementDao;
import com.dai.shared.Measurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
public class MeasurementModelImpl implements MeasurementModel {
private MeasurementDao measurementDao;

   @Autowired
    public MeasurementModelImpl(MeasurementDao measurementDao) {
        this.measurementDao = measurementDao;
    }

    @Override
    public Future<Measurement> readLastMeasurement() {
        return measurementDao.getLatestMeasurement();
    }
}
