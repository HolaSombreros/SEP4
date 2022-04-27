package com.dai.shared;

import java.time.LocalDateTime;

public interface SentMeasurement{
    LocalDateTime getMeasuredDate();
    double getValue();
}