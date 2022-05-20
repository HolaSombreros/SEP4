package com.dai.dao.temperature;

import com.dai.shared.SentMeasurement;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Future;

public interface TemperatureDao {
    Future<SentMeasurement> getLatest(int areaId);
    Future<List<SentMeasurement>> getAllByDate(int areaId, LocalDate date);
}
