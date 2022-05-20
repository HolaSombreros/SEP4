package com.dai.dao.threshold;

import com.dai.shared.Threshold;
import com.dai.shared.ThresholdType;

import java.util.List;
import java.util.concurrent.Future;

public interface ThresholdDao {
    Future<Threshold> find(int areaId, ThresholdType type);
    Future<Threshold> create(Threshold threshold);
}
