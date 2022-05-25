package com.dai.dao.thresholdLog;

import com.dai.model.ThresholdLogs;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Future;

public interface ThresholdLogDao {
    Future<ThresholdLogs> create(ThresholdLogs log);
    Future<List<ThresholdLogs>> readAllByDate(LocalDate date);
}
