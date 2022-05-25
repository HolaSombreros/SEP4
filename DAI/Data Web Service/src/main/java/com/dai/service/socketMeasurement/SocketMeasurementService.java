package com.dai.service.socketMeasurement;

import com.dai.model.Measurement;
import com.dai.model.SocketData;

public interface SocketMeasurementService {
    Measurement saveSocketData(SocketData socketData) throws Exception;
}
