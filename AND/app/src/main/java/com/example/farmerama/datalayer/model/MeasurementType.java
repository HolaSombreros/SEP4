package com.example.farmerama.datalayer.model;

import androidx.annotation.NonNull;

public enum MeasurementType {
    TEMPERATURE("Temperature"), HUMIDITY("Humidity"), SPL("Sound Pressure Level");
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
        }
        return "";
    }
}
