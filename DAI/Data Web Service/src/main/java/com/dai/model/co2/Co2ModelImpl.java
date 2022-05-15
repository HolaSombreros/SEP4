package com.dai.model.co2;

import com.dai.helpers.Helper;
import com.dai.dao.co2.Co2Dao;
import com.dai.shared.SentMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class Co2ModelImpl implements Co2Model {
    private Co2Dao co2Dao;

    @Autowired
    public Co2ModelImpl(Co2Dao co2Dao) {
        this.co2Dao = co2Dao;
    }

    @Override
    public List<SentMeasurement> getLatestCo2(int areaId) throws Exception {
        return new ArrayList<>(List.of(Helper.await(co2Dao.getLatestCo2(areaId))));
    }

    @Override
    public List<SentMeasurement> getAllCo2sInDate(int areaId, LocalDate date) throws Exception {
        return Helper.await(co2Dao.getAllCo2sInDate(areaId, Date.valueOf(date)));
    }
}
