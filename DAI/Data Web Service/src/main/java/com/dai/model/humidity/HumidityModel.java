package com.dai.model.humidity;

import com.dai.shared.SentMeasurement;

import java.util.List;
import java.util.concurrent.Future;

public interface HumidityModel {

    List<SentMeasurement> readLatestHumidity(int areaId) throws Exception;
}
