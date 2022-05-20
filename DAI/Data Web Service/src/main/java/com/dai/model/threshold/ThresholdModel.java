package com.dai.model.threshold;

import com.dai.shared.SentThresholdLog;
import com.dai.shared.ThresholdType;

import java.time.LocalDate;
import java.util.List;

import com.dai.shared.Threshold;
import com.dai.shared.ThresholdType;

import java.util.List;

public interface ThresholdModel {
    Threshold find(int areaId, ThresholdType type) throws Exception;
    Threshold create(Threshold threshold) throws Exception;
    List<SentThresholdLog> getAllExceeding(int areaId, ThresholdType type, LocalDate date) throws Exception;
}
