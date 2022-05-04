package com.dai.dao.area;

import com.dai.repository.AreaRepository;
import com.dai.shared.Area;
import com.dai.shared.Barn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.Future;

@Repository
@EnableAsync
public class AreaDaoImpl implements AreaDao{
    private AreaRepository repository;

    @Autowired
    public AreaDaoImpl(AreaRepository repository) {
        this.repository = repository;
    }

    @Override
    @Async
    public Future<Area> create(Area area) {
        return new AsyncResult<>(repository.save(area));
    }

    @Override
    public Future<Area> read(int id) {
        return new AsyncResult<>(repository.findById(id).get());
    }

    @Override
    public Area update(Area area) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
    @Override
    public Future<List<Area>> getAll() {
        return new AsyncResult<>(repository.findAll());
    }
}
