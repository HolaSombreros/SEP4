package com.dai.service.co2;

import com.dai.dao.area.AreaDao;
import com.dai.exceptions.BadRequestException;
import com.dai.helpers.Helper;
import com.dai.dao.co2.Co2Dao;
import com.dai.model.Area;
import com.dai.model.SentMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class Co2ServiceImpl implements Co2Service {
    private Co2Dao co2Dao;
    private AreaDao areaDao;

    @Autowired
    public Co2ServiceImpl(Co2Dao co2Dao, AreaDao areaDao) {
        this.co2Dao = co2Dao;
        this.areaDao = areaDao;
    }

    @Override
    public List<SentMeasurement> readLatestByAreaId(int areaId) throws Exception {
        try {
            Area area = Helper.await(areaDao.read(areaId));
        }catch (Exception e){
            throw new BadRequestException("Area with the given id doesn't exist");
        }
        return new ArrayList<>(List.of(Helper.await(co2Dao.readLatestByAreaId(areaId))));
    }
    @Override
    public List<SentMeasurement> readAllByDateAndAreaId(int areaId, LocalDate date) throws Exception {
        try {
            Area area = Helper.await(areaDao.read(areaId));
        }catch (Exception e){
            throw new BadRequestException("Area with the given id doesn't exist");
        }
        return Helper.await(co2Dao.readAllByDateAndAreaId(areaId, date));
    }

    @Override
    public List<SentMeasurement> readAllFromTodayByAreaId(int areaId) throws Exception {
        try {
            Area area = Helper.await(areaDao.read(areaId));
        }catch (Exception e){
            throw new BadRequestException("Area with the given id doesn't exist");
        }
        return Helper.await(co2Dao.readAllByDateAndAreaId(areaId, LocalDate.now()));
    }
}
