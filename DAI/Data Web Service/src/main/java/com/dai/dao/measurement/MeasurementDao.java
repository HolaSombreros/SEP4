package com.dai.dao.measurement;

import com.dai.shared.Area;
import com.dai.shared.Measurement;

import java.time.LocalDateTime;
import java.util.concurrent.Future;

public interface MeasurementDao {

    Future<Measurement> create(LocalDateTime measuredDate, double temperature, double humidity, int co2, double sound, Hardware hardware, Area area);
    Future<Measurement> read(int id);
    Future<Measurement> update(Measurement measurement);
    void delete(int id);
    Future<Measurement> getLatestMeasurement(int id);
}
