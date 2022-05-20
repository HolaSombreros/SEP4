package com.dai.dao.threshold;

import com.dai.repository.ThresholdRepository;
import com.dai.shared.Threshold;
import com.dai.shared.ThresholdType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.Future;

@Repository
@EnableAsync
public class ThresholdDaoImpl implements ThresholdDao{

    private ThresholdRepository repository;

    @Autowired
    public ThresholdDaoImpl(ThresholdRepository repository) {
        this.repository = repository;
    }

    @Override
    public Future<Threshold> find(int areaId, ThresholdType type) {
        return new AsyncResult<>(repository.findFirstByAreaIdEqualsAndTypeEquals(areaId, type));
    }

    @Override
    public Future<Threshold> create(Threshold threshold) {
        return new AsyncResult<>(repository.save(threshold));
    }
}
