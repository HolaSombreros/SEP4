package com.dai.dao.threshold;

import com.dai.repository.ThresholdRepository;
import com.dai.model.ThresholdType;
import com.dai.model.Threshold;
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
    public Future<Threshold> readByAreaIdAndType(int areaId, ThresholdType type) {
        return new AsyncResult<>(repository.findFirstByAreaAreaIdEqualsAndTypeEquals(areaId, type));
    }

    @Override
    public Future<Threshold> create(Threshold threshold) {
        return new AsyncResult<>(repository.save(threshold));
    }

    @Override
    public Future<Threshold> update(Threshold threshold) throws Exception {
        return new AsyncResult<>(repository.save(threshold));
    }

    @Override
    public Future<Threshold> read(int id) {
        return new AsyncResult<>(repository.findById(id).get());
    }

    @Override
    public Future<List<Threshold>> readAllByAreaId(int id) {
        return new AsyncResult<>(repository.getAllByAreaAreaId(id));
    }
}
