package com.dai.dao.co2;

import com.dai.model.SentThresholdLog;
import com.dai.model.ThresholdType;
import com.dai.repository.Co2Repository;
import com.dai.model.SentMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Future;


@Repository
@EnableAsync
public class Co2DaoImpl implements Co2Dao {

    private Co2Repository co2Repository;

    @Autowired
    public Co2DaoImpl(Co2Repository co2Repository) {
        this.co2Repository = co2Repository;
    }

    @Override
    public Future<SentMeasurement> readLatestByAreaId(int areaId) {
        return new AsyncResult<>(co2Repository.getLatestCo2(areaId));
    }

    @Override
    public AsyncResult<List<SentMeasurement>> readAllByDateAndAreaId(int areaId, LocalDate date) {
        return new AsyncResult<List<SentMeasurement>>(co2Repository.getAllCo2sInDate(areaId, Date.valueOf(date)));

    }

    @Override
    public Future<List<SentThresholdLog>> getAllExceedingThresholdChanges(int areaId, ThresholdType type, LocalDate date) {
        List<SentThresholdLog> max = co2Repository.getAllExceedingMax(areaId, type.getType(), Date.valueOf(date));
        max.addAll(co2Repository.getAllExceedingMin(areaId, type.getType(), Date.valueOf(date)));
        return new AsyncResult<>(max);
    }
}
