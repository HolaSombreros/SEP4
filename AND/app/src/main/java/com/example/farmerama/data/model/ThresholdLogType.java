package com.example.farmerama.data.model;

import androidx.room.TypeConverters;

@TypeConverters
public enum ThresholdLogType {
    MINIMUM("Minimum"),
    MAXIMUM("Maximum");

    String logType;

    ThresholdLogType(){}
    ThresholdLogType(String logType) {
        this.logType = logType;
    }

    public String getLogType() {
        return logType;
    }
}
