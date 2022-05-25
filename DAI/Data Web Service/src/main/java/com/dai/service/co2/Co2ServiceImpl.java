package com.dai.service.co2;

import com.dai.helpers.Helper;
import com.dai.dao.co2.Co2Dao;
import com.dai.model.SentMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class Co2ServiceImpl implements Co2Service {
    private Co2Dao co2Dao;

    @Autowired
    public Co2ServiceImpl(Co2Dao co2Dao) {
        this.co2Dao = co2Dao;
    }

    @Override
    public List<SentMeasurement> readLatestByAreaId(int areaId) throws Exception {
        return new ArrayList<>(List.of(Helper.await(co2Dao.readLatestByAreaId(areaId))));
    }
    @Override
    public List<SentMeasurement> readAllByDateAndAreaId(int areaId, LocalDate date) throws Exception {
        return Helper.await(co2Dao.readAllByDateAndAreaId(areaId, date));
    }

    @Override
    public List<SentMeasurement> readAllFromTodayByAreaId(int areaId) throws Exception {
        return Helper.await(co2Dao.readAllByDateAndAreaId(areaId, LocalDate.now()));
    }
}
