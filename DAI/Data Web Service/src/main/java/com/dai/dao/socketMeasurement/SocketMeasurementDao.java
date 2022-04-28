package com.dai.dao.socketMeasurement;

import com.dai.shared.Measurement;

import java.util.concurrent.Future;

public interface SocketMeasurementDao {
    public Future<Measurement> saveMeasurement(Measurement measurement);
}
