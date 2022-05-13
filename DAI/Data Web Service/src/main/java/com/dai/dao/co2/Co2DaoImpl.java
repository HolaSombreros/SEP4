package com.dai.dao.co2;

import com.dai.repository.Co2Repository;
import com.dai.shared.SentMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.concurrent.Future;


@Repository
@EnableAsync
public class Co2DaoImpl implements Co2Dao{

    private Co2Repository co2Repository;

    @Autowired
    public Co2DaoImpl(Co2Repository co2Repository) {
        this.co2Repository = co2Repository;
    }

    @Override
    public Future<SentMeasurement> getLatestCo2(int areaId) {
        return new AsyncResult<>(co2Repository.getLatestCo2(areaId));
    }

    @Override
    public AsyncResult<List<SentMeasurement>> getAllCo2sInDate(int areaId, Date date) {
        return new AsyncResult<List<SentMeasurement>>(co2Repository.getAllCo2sInDate(areaId, date));

    }


}
