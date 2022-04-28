package com.dai.dao.hardware;

import com.dai.repository.HardwareRepository;
import com.dai.shared.Hardware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Repository;

import java.util.concurrent.Future;

@Repository
@EnableAsync
public class HardwareDaoImpl implements HardwareDao{

    private HardwareRepository hardwareRepository;

    @Autowired
    public HardwareDaoImpl(HardwareRepository hardwareRepository) {
        this.hardwareRepository = hardwareRepository;
    }

    @Override
    @Async
    public Future<Hardware> create(int portNumber) {
        return new AsyncResult<>(hardwareRepository.save(new Hardware(portNumber)));
    }

    @Override
    @Async
    public Future<Hardware> read(int id) {
        return new AsyncResult<>(hardwareRepository.findById(id).get());
    }

    @Override
    public Hardware update(Hardware hardware) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
