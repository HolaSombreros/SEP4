package com.dai.model.socketMeasurement;

import com.dai.shared.Measurement;
import com.dai.shared.SocketData;

import java.util.concurrent.Future;

public interface SocketMeasurementModel {
    Future<Measurement> saveSocketData(SocketData socketData) throws Exception;
}
