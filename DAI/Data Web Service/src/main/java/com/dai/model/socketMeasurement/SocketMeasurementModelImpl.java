package com.dai.model.socketMeasurement;

import com.dai.Helper;
import com.dai.dao.socketMeasurement.SocketMeasurementDaoImpl;
import com.dai.shared.Measurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SocketMeasurementModelImpl implements SocketMeasurementModel{

    private SocketMeasurementDaoImpl socketMeasurementDao;

    @Autowired
    public SocketMeasurementModelImpl(SocketMeasurementDaoImpl socketMeasurementDao) {
        this.socketMeasurementDao = socketMeasurementDao;
    }

    @Override
    public Measurement saveMeasurement(Measurement measurement) throws Exception {
        return Helper.await(socketMeasurementDao.saveMeasurement(measurement));
    }
}
