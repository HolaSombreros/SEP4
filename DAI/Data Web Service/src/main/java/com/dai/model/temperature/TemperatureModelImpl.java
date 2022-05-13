package com.dai.model.temperature;

import com.dai.helpers.Helper;
import com.dai.dao.temperature.TemperatureDao;
import com.dai.shared.SentMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class TemperatureModelImpl implements TemperatureModel {
    private TemperatureDao temperatureDao;

    @Autowired
    public TemperatureModelImpl(TemperatureDao temperatureDao) {
        this.temperatureDao = temperatureDao;
    }

    @Override
    public List<SentMeasurement> readLatestTemperature(int areaId) throws Exception {
        return new ArrayList<>(List.of(Helper.await(temperatureDao.getLatestTemperatureMeasurement(areaId))));
    }

    @Override
    public List<SentMeasurement> readAllTemperatures(int id) throws Exception {
        return Helper.await(temperatureDao.readAllTemperatures(id));
    }

    @Override
    public List<SentMeasurement> getAreaTemperaturesByDate(int areaId, LocalDate date) throws Exception {
        return Helper.await(temperatureDao.getAreaTemperaturesInDate(areaId, date));
    }
}
