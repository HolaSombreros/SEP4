package com.dai.model.humidity;

import com.dai.shared.SentMeasurement;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Future;

public interface HumidityModel {

    List<SentMeasurement> readLatestHumidity(int areaId) throws Exception;
    List<SentMeasurement> getHumidityMeasurementsByDate(int areaId, LocalDate localDate) throws Exception;
}
