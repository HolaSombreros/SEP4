package com.dai.model;

public enum ThresholdType {
    TEMPERATURE("Temperature"), HUMIDITY("Humidity"), CO2("CO₂"), SPL("Sound Pressure Level");

    private String type;

    ThresholdType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
