package com.dai.model.threshold;

import com.dai.shared.*;

import java.time.LocalDate;
import java.util.List;

import com.dai.shared.ThresholdType;

import java.util.List;

public interface ThresholdModel {
    Threshold find(int areaId, ThresholdType type) throws Exception;
    Threshold create(Threshold threshold) throws Exception;
    Threshold update(Threshold toThreshold, int userId) throws Exception;
    List<SentThresholdLog> getAllExceeding(int areaId, ThresholdType type, LocalDate date) throws Exception;
    List<ThresholdLogs> getAllByDate(LocalDate date) throws Exception;
}
