package com.example.farmerama.data.model;

import androidx.annotation.NonNull;

public enum MeasurementType {
    TEMPERATURE("Temperature"), HUMIDITY("Humidity"),  CO2("CO₂"), SPL("Sound Pressure Level");
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
            case "Temperature": return "°C";
            case "Humidity": return "%";
            case "Sound Pressure Level": return "dB";
            case "CO₂": return "ppm";
        }
        return "";
    }
}
