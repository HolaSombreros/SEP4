package com.dai.dao.measurement;

import com.dai.model.Hardware;
import com.dai.model.Measurement;
import com.dai.model.MeasurementType;

import java.time.LocalDateTime;
import java.util.concurrent.Future;

public interface MeasurementDao {

    Future<Measurement> create(MeasurementType type, double value, LocalDateTime measuredDate, Hardware hardware);
    Future<Measurement> read(int id);
    Future<Measurement> update(Measurement measurement);
    void delete(int id);
    Future<Measurement> getLatestByType(MeasurementType type);
}
