package com.example.farmerama.data.model;

import androidx.annotation.NonNull;

public enum MeasurementType {
        TEMPERATURE("TEMPERATURE"), HUMIDITY("HUMIDITY"),  CO2("CO2"), SPL("SOUND PRESSURE LEVEL");
    private String type;

    MeasurementType(String type) {
        this.type = type;
    }

    @NonNull
    public String toString() {
        return type;
    }

    public String toUnit() {
        switch (type) {
            case "TEMPERATURE": return "°C";
            case "HUMIDITY": return "%";
            case "SOUND PRESSURE LEVEL": return "dB";
            case "CO2": return "ppm";
        }
        return "";
    }
}
