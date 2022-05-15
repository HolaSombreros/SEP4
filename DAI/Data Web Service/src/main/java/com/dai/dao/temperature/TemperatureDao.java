package com.dai.dao.temperature;

import com.dai.shared.SentMeasurement;
import org.apache.tomcat.util.net.AprEndpoint;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Future;

public interface TemperatureDao {
    Future<SentMeasurement> getLatestTemperatureMeasurement(int areaId);
    Future<List<SentMeasurement>> readAllTemperatures(int areaId);
    Future<List<SentMeasurement>> getAreaTemperaturesInDate(int areaId, LocalDate date);
}
