package com.dai.dao.humidity;

import com.dai.repository.HumidityRepository;
import com.dai.model.SentMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Future;
@Repository
@EnableAsync
public class HumidityDaoImpl implements HumidityDao{

    private HumidityRepository humidityRepository;

    @Autowired
    public HumidityDaoImpl(HumidityRepository humidityRepository) {
        this.humidityRepository = humidityRepository;
    }



    @Override
    @Async
    public Future<SentMeasurement> readLatestByAreaId(int areaId) {
        return new AsyncResult<>(humidityRepository.getLatestByArea(areaId));
    }

    @Override
    public Future<List<SentMeasurement>> readAllByDateAndAreaId(int areaId, LocalDate date) {
        return new AsyncResult<>(humidityRepository.getAllByAreaAndDate(areaId, Date.valueOf(date)));
    }
}
