package com.dai.service.temperature;

import com.dai.helpers.Helper;
import com.dai.dao.temperature.TemperatureDao;
import com.dai.model.SentMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class TemperatureServiceImpl implements TemperatureService {
    private TemperatureDao temperatureDao;

    @Autowired
    public TemperatureServiceImpl(TemperatureDao temperatureDao) {
        this.temperatureDao = temperatureDao;
    }

    @Override
    public List<SentMeasurement> readLatestByAreaId(int areaId) throws Exception {
        return Helper.await(temperatureDao.readLatestByAreaId(areaId));
    }

    @Override
    public List<SentMeasurement> readAllFromTodayByAreaId(int id) throws Exception {
        return Helper.await(temperatureDao.readAllByAreaIdAndDate(id, LocalDate.now()));
    }

    @Override
    public List<SentMeasurement> readAllByDateAndAreaId(int areaId, LocalDate date) throws Exception {
        return Helper.await(temperatureDao.readAllByAreaIdAndDate(areaId, date));
    }
}
