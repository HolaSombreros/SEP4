package com.dai.shared;

import java.time.LocalDateTime;

public interface SentThresholdLog {
    LocalDateTime getMeasuredDate();
    double getValue();
    double getThreshold();
}
