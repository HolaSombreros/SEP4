package com.dai.service.temperature;

import com.dai.model.SentMeasurement;

import java.time.LocalDate;
import java.util.List;

public interface TemperatureService {
    List<SentMeasurement> readLatestByAreaId(int areaId) throws Exception;
    List<SentMeasurement> readAllFromTodayByAreaId(int id) throws Exception;
    List<SentMeasurement> readAllByDateAndAreaId(int areaId, LocalDate date) throws Exception;
}
