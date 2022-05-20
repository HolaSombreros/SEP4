package com.dai.model.threshold;

import com.dai.dao.threshold.ThresholdDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ThresholdModelImpl implements ThresholdModel{

    private ThresholdDao thresholdDao;

    @Autowired
    public ThresholdModelImpl(ThresholdDao thresholdDao) {
        this.thresholdDao = thresholdDao;
    }
}
