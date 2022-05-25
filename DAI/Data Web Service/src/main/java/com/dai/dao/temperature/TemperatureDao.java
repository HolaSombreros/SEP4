package com.dai.dao.temperature;

import com.dai.model.SentMeasurement;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Future;

public interface TemperatureDao {
    Future<List<SentMeasurement>> readLatestByAreaId(int areaId);
    Future<List<SentMeasurement>> readAllByAreaIdAndDate(int areaId, LocalDate date);
}
