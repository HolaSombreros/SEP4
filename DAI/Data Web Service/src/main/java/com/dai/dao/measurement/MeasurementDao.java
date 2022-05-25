package com.dai.dao.measurement;

import com.dai.model.Measurement;

import java.util.concurrent.Future;

public interface MeasurementDao {
    Future<Measurement> create(Measurement measurement);
}
