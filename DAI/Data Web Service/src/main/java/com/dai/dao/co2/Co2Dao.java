package com.dai.dao.co2;

import com.dai.shared.SentMeasurement;
import org.springframework.scheduling.annotation.AsyncResult;


import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Future;

public interface Co2Dao {
    Future<SentMeasurement> getLatest(int areaId);
    AsyncResult<List<SentMeasurement>> getAllByDate(int areaId, LocalDate date);
}
