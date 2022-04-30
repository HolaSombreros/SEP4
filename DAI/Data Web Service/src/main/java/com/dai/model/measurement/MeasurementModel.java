package com.dai.model.measurement;

import com.dai.shared.Measurement;

public interface MeasurementModel {

    Measurement readLastMeasurement(int id) throws Exception;
}
