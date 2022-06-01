package com.dai.service.temperature;

import com.dai.dao.area.AreaDao;
import com.dai.exceptions.BadRequestException;
import com.dai.helpers.Helper;
import com.dai.dao.temperature.TemperatureDao;
import com.dai.model.Area;
import com.dai.model.SentMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class TemperatureServiceImpl implements TemperatureService {
    private TemperatureDao temperatureDao;
    private AreaDao areaDao;

    @Autowired
    public TemperatureServiceImpl(TemperatureDao temperatureDao, AreaDao areaDao) {
        this.temperatureDao = temperatureDao;
        this.areaDao = areaDao;
    }

    @Override
    public List<SentMeasurement> readLatestByAreaId(int areaId) throws Exception {
        try {
           Area area = Helper.await(areaDao.read(areaId));
           return Helper.await(temperatureDao.readLatestByAreaId(areaId));
        }
        catch (Exception e){
            throw new BadRequestException("Area with the given id doesn't exist");
        }

    }

    @Override
    public List<SentMeasurement> readAllFromTodayByAreaId(int id) throws Exception {
        try {
            Area area = Helper.await(areaDao.read(id));
            return Helper.await(temperatureDao.readAllByAreaIdAndDate(id, LocalDate.now()));
        }
        catch (Exception e){
            throw new BadRequestException("Area with the given id doesn't exist");
        }
    }

    @Override
    public List<SentMeasurement> readAllByDateAndAreaId(int areaId, LocalDate date) throws Exception {
        try {
            Area area = Helper.await(areaDao.read(areaId));
            return Helper.await(temperatureDao.readAllByAreaIdAndDate(areaId, date));
        }
        catch (Exception e){
            throw new BadRequestException("Area with the given id doesn't exist");
        }

    }
}
