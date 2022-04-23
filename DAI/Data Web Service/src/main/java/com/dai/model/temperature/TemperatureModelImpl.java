package com.dai.model.temperature;

import com.dai.Helper;
import com.dai.dao.temperature.TemperatureDao;
import com.dai.shared.SentMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;
@Component
public class TemperatureModelImpl implements TemperatureModel{
    private TemperatureDao temperatureDao;

    @Autowired
    public TemperatureModelImpl(TemperatureDao temperatureDao) {
        this.temperatureDao = temperatureDao;
    }

    @Override
    public SentMeasurement readLatestTemperature(int areaId) throws Exception {
            return Helper.await(temperatureDao.getLatestTemperatureMeasurement(areaId));

    }
}
