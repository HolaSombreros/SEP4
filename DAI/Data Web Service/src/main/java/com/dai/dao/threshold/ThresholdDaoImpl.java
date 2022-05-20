package com.dai.dao.threshold;

import com.dai.repository.ThresholdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Repository;

@Repository
@EnableAsync
public class ThresholdDaoImpl implements ThresholdDao{

    private ThresholdRepository repository;

    @Autowired
    public ThresholdDaoImpl(ThresholdRepository repository) {
        this.repository = repository;
    }
}
