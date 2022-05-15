package com.dai.dao.measurement;

import com.dai.shared.Measurement;

import java.util.concurrent.Future;

public interface MeasurementDao {
    public Future<Measurement> saveMeasurement(Measurement measurement);
}
