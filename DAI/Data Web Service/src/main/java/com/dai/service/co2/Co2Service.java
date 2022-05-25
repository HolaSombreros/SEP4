package com.dai.service.co2;

import com.dai.model.SentMeasurement;

import java.time.LocalDate;
import java.util.List;

public interface Co2Service {
    List<SentMeasurement> readLatestByAreaId(int areaId) throws Exception;
    List<SentMeasurement> readAllByDateAndAreaId(int areaId, LocalDate date) throws Exception;
    List<SentMeasurement> readAllFromTodayByAreaId(int areaId) throws Exception;
}
