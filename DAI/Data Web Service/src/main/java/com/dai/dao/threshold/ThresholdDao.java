package com.dai.dao.threshold;

import com.dai.model.SentThresholdLog;
import com.dai.model.ThresholdType;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Future;

import com.dai.model.Threshold;

public interface ThresholdDao {

    Future<Threshold> readByAreaIdAndType(int areaId, ThresholdType type);
    Future<Threshold> create(Threshold threshold);
    Future<Threshold> update(Threshold toThreshold) throws Exception;
    Future<Threshold> read(int id);
    Future<List<Threshold>> readAllByAreaId(int id);
}
