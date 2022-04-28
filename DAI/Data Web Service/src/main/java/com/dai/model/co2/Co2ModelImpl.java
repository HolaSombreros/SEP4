package com.dai.model.co2;

import com.dai.Helper;
import com.dai.dao.co2.Co2Dao;
import com.dai.shared.SentMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Co2ModelImpl implements Co2Model {
    private Co2Dao co2Dao;

    @Autowired
    public Co2ModelImpl(Co2Dao co2Dao) {
        this.co2Dao = co2Dao;
    }

    @Override
    public SentMeasurement readLatestTemperature(int areaId) throws Exception {
        return Helper.await(co2Dao.readLatestTemperature(areaId));
    }
}
