package com.dai.model.temperature;

import com.dai.helpers.Helper;
import com.dai.dao.temperature.TemperatureDao;
import com.dai.shared.SentMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TemperatureModelImpl implements TemperatureModel{
    private TemperatureDao temperatureDao;

    @Autowired
    public TemperatureModelImpl(TemperatureDao temperatureDao) {
        this.temperatureDao = temperatureDao;
    }

    @Override
    public List<SentMeasurement> readLatestTemperature(int areaId) throws Exception {

            return new ArrayList<>(List.of(Helper.await(temperatureDao.getLatestTemperatureMeasurement(areaId))));

    }
}
