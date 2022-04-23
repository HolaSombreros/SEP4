package com.dai.dao.humidity;

import com.dai.repository.HumidityRepository;
import com.dai.shared.SentMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Repository;

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
    public Future<SentMeasurement> getLatestHumidityMeasurement(int areaId) {
        return null;
    }
}
