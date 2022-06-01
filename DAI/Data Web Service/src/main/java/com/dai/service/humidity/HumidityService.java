package com.dai.service.humidity;

import com.dai.model.SentMeasurement;

import java.time.LocalDate;
import java.util.List;

public interface HumidityService {

    List<SentMeasurement> readLatestByAreaId(int areaId) throws Exception;
    List<SentMeasurement> readAllByDateAndAreaId(int areaId, LocalDate localDate) throws Exception;
    List<SentMeasurement> readAllFromTodayByAreaId(int areaId) throws Exception;
}
