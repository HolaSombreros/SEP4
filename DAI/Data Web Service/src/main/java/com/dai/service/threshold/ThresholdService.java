package com.dai.service.threshold;

import com.dai.model.*;

import java.time.LocalDate;
import java.util.List;

import com.dai.model.ThresholdType;

public interface ThresholdService {
    Threshold readByAreaIdAndType(int areaId, ThresholdType type) throws Exception;
    Threshold create(Threshold threshold) throws Exception;
    Threshold update(Threshold toThreshold, int userId) throws Exception;
    List<SentThresholdLog> readAllExceedingLogsByTypeAndAreaIdAndDate(int areaId, ThresholdType type, LocalDate date) throws Exception;
    List<ThresholdLogs> readAllLogsByDate(LocalDate date) throws Exception;

    Threshold read(int id) throws Exception;
}
