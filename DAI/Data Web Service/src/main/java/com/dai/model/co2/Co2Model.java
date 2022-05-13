package com.dai.model.co2;

import com.dai.shared.SentMeasurement;

import java.util.List;

public interface Co2Model {
    List<SentMeasurement> readLatestCo2(int areaId) throws Exception;
}
