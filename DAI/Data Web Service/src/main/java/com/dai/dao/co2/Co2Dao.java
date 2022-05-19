package com.dai.dao.co2;

import com.dai.shared.SentMeasurement;
import org.springframework.scheduling.annotation.AsyncResult;


import java.sql.Date;
import java.util.List;
import java.util.concurrent.Future;

public interface Co2Dao {
    Future<SentMeasurement> getLatestCo2(int areaId);
    AsyncResult<List<SentMeasurement>> getAllCo2sInDate(int areaId, Date date);
}
