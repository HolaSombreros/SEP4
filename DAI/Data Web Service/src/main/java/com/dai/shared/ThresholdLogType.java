package com.dai.shared;

public enum ThresholdLogType {
    MINIMUM("minimum"),
    MAXIMUM("maximum");

    String type;

    ThresholdLogType(String type) {
        this.type = type;
    }
}
