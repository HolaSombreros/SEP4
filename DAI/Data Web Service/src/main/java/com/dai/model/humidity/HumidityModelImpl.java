package com.dai.model.humidity;

import com.dai.helpers.Helper;
import com.dai.dao.humidity.HumidityDao;
import com.dai.shared.SentMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class HumidityModelImpl implements HumidityModel{

    private HumidityDao humidityDao;

    @Autowired
    public HumidityModelImpl(HumidityDao humidityDao) {
        this.humidityDao = humidityDao;
    }


    @Override
    public List<SentMeasurement> readLatest(int areaId) throws Exception {
        return new ArrayList<>(List.of(Helper.await(humidityDao.getLatest(areaId))));
    }

    @Override
    public List<SentMeasurement> getAllByDate(int areaId, LocalDate localDate) throws Exception {
        return Helper.await(humidityDao.getAllByDate(areaId, localDate));
    }

    @Override
    public List<SentMeasurement> getAllFromToday(int areaId) throws Exception {
        return Helper.await(humidityDao.getAllByDate(areaId, LocalDate.now()));
    }
}