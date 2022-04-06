package com.dai.dao.measurement;

import com.dai.shared.Hardware;
import com.dai.shared.Measurement;
import com.dai.shared.MeasurementType;

import java.time.LocalDateTime;
import java.util.concurrent.Future;

public interface MeasurementDao {

    Future<Measurement> create(LocalDateTime measuredDate, double temperature, double humidity, int co2, double sound, Hardware hardware);
    Future<Measurement> read(int id);
    Future<Measurement> update(Measurement measurement);
    void delete(int id);
    Future<Measurement> getLatestByType();
}
