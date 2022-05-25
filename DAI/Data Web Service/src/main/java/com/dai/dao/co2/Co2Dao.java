package com.dai.dao.co2;

import com.dai.model.SentMeasurement;
import org.springframework.scheduling.annotation.AsyncResult;


import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Future;

public interface Co2Dao {
    Future<SentMeasurement> readLatestByAreaId(int areaId);
    AsyncResult<List<SentMeasurement>> readAllByDateAndAreaId(int areaId, LocalDate date);
}
