package com.dai.dao.area;

import com.dai.repository.AreaRepository;
import com.dai.shared.Area;
import com.dai.shared.Barn;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Repository;

import java.util.concurrent.Future;

@Repository
@EnableAsync
public class AreaDaoImpl implements AreaDao{
    private AreaRepository repository;

    public AreaDaoImpl(AreaRepository repository) {
        this.repository = repository;
    }

    @Override
    @Async
    public Future<Area> create(Barn barn, String name, String description, int numberOfPigs) {
        return new AsyncResult<>(repository.save(new Area(barn, name, description, numberOfPigs)));
    }

    @Override
    public Future<Area> read(int id) {
        return new AsyncResult<>(repository.getById(id));
    }

    @Override
    public Area update(Area area) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
