package com.dai.model.humidity;

import com.dai.Helper;
import com.dai.dao.humidity.HumidityDao;
import com.dai.shared.SentMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;
@Component
public class HumidityModelImpl implements HumidityModel{

    private HumidityDao humidityDao;

    @Autowired
    public HumidityModelImpl(HumidityDao humidityDao) {
        this.humidityDao = humidityDao;
    }


    @Override
    public SentMeasurement readLatestHumidity(int areaId) throws Exception {
        return Helper.await(humidityDao.getLatestHumidityMeasurement(areaId));
    }
}
