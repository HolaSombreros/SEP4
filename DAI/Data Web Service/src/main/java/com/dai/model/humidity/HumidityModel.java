package com.dai.model.humidity;

import com.dai.shared.SentMeasurement;

import java.util.concurrent.Future;

public interface HumidityModel {

    SentMeasurement readLatestHumidity(int areaId) throws Exception;
}
