package com.dai.model.temperature;

import com.dai.shared.SentMeasurement;

import java.time.LocalDate;
import java.util.List;

public interface TemperatureModel {
    List<SentMeasurement> readLatestTemperature(int areaId) throws Exception;
    List<SentMeasurement> readAllTemperatures(int id) throws Exception;
    List<SentMeasurement> getAreaTemperaturesByDate(int areaId, LocalDate date) throws Exception;
}
