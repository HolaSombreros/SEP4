package com.dai.model.socketMeasurement;

import com.dai.shared.Measurement;
import com.dai.shared.SocketData;

public interface SocketMeasurementModel {
    Measurement saveSocketData(SocketData socketData) throws Exception;
}
