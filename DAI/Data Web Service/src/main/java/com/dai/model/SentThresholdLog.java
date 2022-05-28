package com.dai.model;

import java.time.LocalDateTime;

public interface SentThresholdLog {
    LocalDateTime getMeasuredDate();
    double getValue();
    double getThreshold();
    int getMeasurementId();

    String getType();
}