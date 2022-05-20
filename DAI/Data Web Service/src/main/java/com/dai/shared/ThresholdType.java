package com.dai.shared;

public enum ThresholdType {
    TEMPERATURE("Temperature"),
    HUMIDITY("Humidity"),
    CO2("Co2"),
    SOUND("Sound");

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
