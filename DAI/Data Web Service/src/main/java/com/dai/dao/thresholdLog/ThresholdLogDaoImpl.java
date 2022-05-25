package com.dai.dao.thresholdLog;

import com.dai.repository.ThresholdLogsRepository;
import com.dai.model.ThresholdLogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Future;

@Repository
@EnableAsync
public class ThresholdLogDaoImpl implements ThresholdLogDao{

    private ThresholdLogsRepository repository;

    @Autowired
    public ThresholdLogDaoImpl(ThresholdLogsRepository repository) {
        this.repository = repository;
    }

    public Future<ThresholdLogs> create(ThresholdLogs log) {
        return new AsyncResult<>(repository.save(log));
    }

    @Override
    public Future<List<ThresholdLogs>> readAllByDate(LocalDate date) {
        return new AsyncResult<>(repository.findAllByChangedOnLike(date));
    }
}
