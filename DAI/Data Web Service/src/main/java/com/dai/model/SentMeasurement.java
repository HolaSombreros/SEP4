package com.dai.model;

import java.time.LocalDateTime;

public interface SentMeasurement{
    LocalDateTime getMeasuredDate();
    double getValue();
    int getMeasurementId();
    int getAreaId();
}