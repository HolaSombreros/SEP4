package com.dai.service.humidity;

import com.dai.dao.area.AreaDao;
import com.dai.exceptions.BadRequestException;
import com.dai.helpers.Helper;
import com.dai.dao.humidity.HumidityDao;
import com.dai.model.Area;
import com.dai.model.SentMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class HumidityServiceImpl implements HumidityService {

    private HumidityDao humidityDao;
    private AreaDao areaDao;

    @Autowired
    public HumidityServiceImpl(HumidityDao humidityDao, AreaDao areaDao) {
        this.humidityDao = humidityDao;
        this.areaDao = areaDao;
    }


    @Override
    public List<SentMeasurement> readLatestByAreaId(int areaId) throws Exception {
        try {
            Area area = Helper.await(areaDao.read(areaId));
            return new ArrayList<>(List.of(Helper.await(humidityDao.readLatestByAreaId(areaId))));
        }catch (Exception e){
            throw new BadRequestException("Area with the given id doesn't exist");
        }

    }

    @Override
    public List<SentMeasurement> readAllByDateAndAreaId(int areaId, LocalDate localDate) throws Exception {
        try {
            Area area = Helper.await(areaDao.read(areaId));
            return Helper.await(humidityDao.readAllByDateAndAreaId(areaId, localDate));
        }catch (Exception e){
            throw new BadRequestException("Area with the given id doesn't exist");
        }

    }

    @Override
    public List<SentMeasurement> readAllFromTodayByAreaId(int areaId) throws Exception {
        try {
            Area area = Helper.await(areaDao.read(areaId));
            return Helper.await(humidityDao.readAllByDateAndAreaId(areaId, LocalDate.now()));
        }catch (Exception e){
            throw new BadRequestException("Area with the given id doesn't exist");
        }

    }
}
