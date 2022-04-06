package com.dai.model.measurement;

import com.dai.shared.Measurement;

import java.util.concurrent.Future;

public interface MeasurementModel {

    Future<Measurement> readLastMeasurement();
}
