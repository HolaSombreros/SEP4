package com.dai.model.temperature;

import com.dai.shared.SentMeasurement;

import java.time.LocalDate;
import java.util.List;

public interface TemperatureModel {
    List<SentMeasurement> getLatest(int areaId) throws Exception;
    List<SentMeasurement> getAllFromToday(int id) throws Exception;
    List<SentMeasurement> getAllByDate(int areaId, LocalDate date) throws Exception;
}
