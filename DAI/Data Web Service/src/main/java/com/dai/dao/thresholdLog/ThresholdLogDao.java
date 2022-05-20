package com.dai.dao.thresholdLog;

import com.dai.shared.ThresholdLogs;

import java.util.concurrent.Future;

public interface ThresholdLogDao {
    public Future<ThresholdLogs> createLog(ThresholdLogs log);
}
