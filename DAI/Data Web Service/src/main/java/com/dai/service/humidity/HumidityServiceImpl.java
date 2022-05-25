package com.dai.service.humidity;

import com.dai.helpers.Helper;
import com.dai.dao.humidity.HumidityDao;
import com.dai.model.SentMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class HumidityServiceImpl implements HumidityService {

    private HumidityDao humidityDao;

    @Autowired
    public HumidityServiceImpl(HumidityDao humidityDao) {
        this.humidityDao = humidityDao;
    }


    @Override
    public List<SentMeasurement> readLatestByAreaId(int areaId) throws Exception {
        return new ArrayList<>(List.of(Helper.await(humidityDao.readLatestByAreaId(areaId))));
    }

    @Override
    public List<SentMeasurement> readAllByDateAndAreaId(int areaId, LocalDate localDate) throws Exception {
        return Helper.await(humidityDao.readAllByDateAndAreaId(areaId, localDate));
    }

    @Override
    public List<SentMeasurement> readAllFromTodayByAreaId(int areaId) throws Exception {
        return Helper.await(humidityDao.readAllByDateAndAreaId(areaId, LocalDate.now()));
    }
}
