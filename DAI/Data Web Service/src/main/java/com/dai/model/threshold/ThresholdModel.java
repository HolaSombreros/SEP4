package com.dai.model.threshold;

import com.dai.shared.Threshold;
import com.dai.shared.ThresholdType;

public interface ThresholdModel {
    Threshold find(int areaId, ThresholdType type) throws Exception;
    Threshold create(Threshold threshold) throws Exception;
    Threshold update(Threshold toThreshold, int userId) throws Exception;
}
