package com.dai.dao.barn;

import com.dai.repository.BarnRepository;
import com.dai.shared.Barn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Repository;

import java.util.concurrent.Future;

@Repository
@EnableAsync
public class BarnDaoImpl implements BarnDao {
    private BarnRepository barnRepository;

    @Autowired
    public BarnDaoImpl(BarnRepository barnRepository) {
        this.barnRepository = barnRepository;
    }

    @Override
    @Async
    public Future<Barn> create(String name) {
        return new AsyncResult<>(barnRepository.save(new Barn(name)));
    }

    @Override
    @Async
    public Future<Barn> read(int id) {
        return new AsyncResult<>(barnRepository.findById(id).get());
    }

    @Override
    public Barn update(Barn barn) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
