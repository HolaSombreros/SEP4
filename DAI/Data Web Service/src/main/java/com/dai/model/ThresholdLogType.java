package com.dai.model;

public enum ThresholdLogType {
    MINIMUM("minimum"),
    MAXIMUM("maximum");

    String type;

    ThresholdLogType(String type) {
        this.type = type;
    }
}
