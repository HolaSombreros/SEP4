package com.dai.dao.humidity;

import com.dai.shared.SentMeasurement;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Future;

public interface HumidityDao {
    Future<SentMeasurement> getLatest(int areaId);
    Future<List<SentMeasurement>> getAllByDate(int areaId, LocalDate date);
}
