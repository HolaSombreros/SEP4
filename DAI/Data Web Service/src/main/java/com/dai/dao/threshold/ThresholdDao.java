package com.dai.dao.threshold;

import com.dai.shared.SentThresholdLog;
import com.dai.shared.ThresholdType;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Future;

import com.dai.shared.Threshold;
import com.dai.shared.ThresholdType;
import org.apache.tomcat.jni.Local;

import java.util.List;
import java.util.concurrent.Future;

public interface ThresholdDao {

    Future<List<SentThresholdLog>> getAllExceedingMax(int areaId, ThresholdType type, LocalDate date);
    Future<List<SentThresholdLog>> getAllExceedingMin(int areaId, ThresholdType type, LocalDate date);
    Future<Threshold> find(int areaId, ThresholdType type);
    Future<Threshold> create(Threshold threshold);
}
