package com.dai.dao.barn;

import com.dai.repository.BarnRepository;
import com.dai.model.Barn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
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
    public Future<Barn> create(Barn barn) {
        return new AsyncResult<>(barnRepository.save(barn));
    }

    @Override
    public Future<Barn> read(int id) {
        Optional<Barn> byId = barnRepository.findById(id);
        return new AsyncResult<>(byId.get());
    }

    @Override
    public Future<Barn> update(Barn barn) {
        Barn entity = barnRepository.findById(barn.getId()).get();
        entity.setName(barn.getName());
        return new AsyncResult<>(barnRepository.save(entity));
    }

    @Override
    public Future<Barn> delete(int id) {
        return new AsyncResult<>(barnRepository.deleteById(id));
    }

    @Override
    public Future<List<Barn>> readAll() {
        return new AsyncResult<>(barnRepository.findAll());
    }
}
