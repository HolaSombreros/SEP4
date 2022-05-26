package com.dai.dao;

import com.dai.dao.co2.Co2Dao;
import com.dai.dao.humidity.HumidityDao;
import com.dai.dao.sound.SoundDao;
import com.dai.dao.temperature.TemperatureDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MeasurementDaoFactory {
    private TemperatureDao temperatureDao;
    private HumidityDao humidityDao;
    private Co2Dao co2Dao;
    private SoundDao soundDao;

    @Autowired
    public MeasurementDaoFactory(TemperatureDao temperatureDao, HumidityDao humidityDao, Co2Dao co2Dao, SoundDao soundDao) {
        this.temperatureDao = temperatureDao;
        this.humidityDao = humidityDao;
        this.co2Dao = co2Dao;
        this.soundDao = soundDao;
    }

    public TemperatureDao getTemperatureDao() {
        return temperatureDao;
    }

    public HumidityDao getHumidityDao() {
        return humidityDao;
    }

    public Co2Dao getCo2Dao() {
        return co2Dao;
    }

    public SoundDao getSoundDao() {
        return soundDao;
    }
}
