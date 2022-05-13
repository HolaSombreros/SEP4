package com.example.farmerama.datalayer.model;

import androidx.annotation.NonNull;

public enum MeasurementType {
    TEMPERATURE("Temperature"), HUMIDITY("Humidity"), CO2("CO₂");
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
            case "CO₂": return "ppm";
        }
        return "";
    }
}
