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
    public List<SentMeasurement> getLatest(int areaId) throws Exception {
        return Helper.await(temperatureDao.getLatest(areaId));
    }

    @Override
    public List<SentMeasurement> getAllFromToday(int id) throws Exception {
        return Helper.await(temperatureDao.getAllByDate(id, LocalDate.now()));
    }

    @Override
    public List<SentMeasurement> getAllByDate(int areaId, LocalDate date) throws Exception {
        return Helper.await(temperatureDao.getAllByDate(areaId, date));
    }
}
