package com.dai.dao.thresholdLog;

import com.dai.shared.ThresholdLogs;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Future;

public interface ThresholdLogDao {
    Future<ThresholdLogs> createLog(ThresholdLogs log);
    Future<List<ThresholdLogs>> getAllByDate(LocalDate date);
}
