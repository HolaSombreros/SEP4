package com.dai.model.socketMeasurement;

import com.dai.shared.Measurement;

public interface SocketMeasurementModel {
    public Measurement saveMeasurement(Measurement measurement) throws Exception;
}
