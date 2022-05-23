package com.example.farmerama.data.model;

public enum ThresholdLogType {
    MINIMUM("Minimum"),
    MAXIMUM("Maximum");

    String type;

    ThresholdLogType(String type) {
        this.type = type;
    }
}
