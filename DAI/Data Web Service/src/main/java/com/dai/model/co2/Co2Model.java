package com.dai.model.co2;

import com.dai.shared.SentMeasurement;

public interface Co2Model {
    SentMeasurement readLatestTemperature(int areaId) throws Exception;
}
