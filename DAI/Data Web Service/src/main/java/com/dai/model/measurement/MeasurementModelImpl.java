package com.dai.model.measurement;

import com.dai.Helper;
import com.dai.dao.measurement.MeasurementDao;
import com.dai.shared.Measurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MeasurementModelImpl implements MeasurementModel {
private MeasurementDao measurementDao;

   @Autowired
    public MeasurementModelImpl(MeasurementDao measurementDao) {
        this.measurementDao = measurementDao;
    }

    @Override
    public Measurement readLastMeasurement(int id) throws Exception {
        return Helper.await(measurementDao.getLatestMeasurement(id));
    }
}
