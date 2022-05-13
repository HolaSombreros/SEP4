package com.dai.model.humidity;

import com.dai.shared.SentMeasurement;

import java.time.LocalDate;
import java.util.List;

public interface HumidityModel {

    List<SentMeasurement> readLatest(int areaId) throws Exception;
    List<SentMeasurement> getAllByDate(int areaId, LocalDate localDate) throws Exception;
    List<SentMeasurement> getAllFromToday(int areaId) throws Exception;
}
