package com.dai.dao.area;

import com.dai.repository.AreaRepository;
import com.dai.model.Area;
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
    public Future<Area> update(Area area) {
        Area byId = repository.findById(area.getAreaId()).get();
        byId.setName(area.getName());
        byId.setDescription(area.getDescription());
        byId.setHardwareId(area.getHardwareId());
        byId.setNumberOfPigs(area.getNumberOfPigs());
        return new AsyncResult<>(repository.save(byId));
    }
    @Override
    public Future<Area> readByHardwareId(String id) {
        return new AsyncResult<>(repository.getFirstByHardwareIdEquals(id));
    }

    @Override
    public Future<List<Area>> readAll() {
        return new AsyncResult<>(repository.findAll());
    }

    @Override
    public Future<Boolean> readByNameAndBarn(String name, int barnId) {
        return new AsyncResult<>(repository.existsByNameAndBarnBarnId(name, barnId));
    }

    @Override
    public Future<Area> delete(int id) {
        Area area = repository.findById(id).get();
        repository.deleteById(id);
        return new AsyncResult<>(area);
    }
}
