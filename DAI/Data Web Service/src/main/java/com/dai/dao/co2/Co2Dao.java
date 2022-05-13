package com.dai.dao.co2;

import com.dai.shared.SentMeasurement;

import java.util.concurrent.Future;

public interface Co2Dao {
    Future<SentMeasurement> readLatestCo2(int areaId);
}
