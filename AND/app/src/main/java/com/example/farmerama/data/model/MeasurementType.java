package com.example.farmerama.data.model;

import androidx.annotation.NonNull;
import androidx.room.TypeConverters;

@TypeConverters
public enum MeasurementType {
        TEMPERATURE("TEMPERATURE"), HUMIDITY("HUMIDITY"),  CO2("CO2"), SOUND("SOUND");
    private String type;

    MeasurementType(String type) {
        this.type = type;
    }

    @NonNull
    public String toString() {
        return type;
    }

    public float getMaximum() {
        switch (type) {
            case "TEMPERATURE": return 60;
            case "HUMIDITY": return 100;
            case "SOUND": return 120;
            case "CO2": return 3000;
            default: return 0;
        }
    }

    public static MeasurementType formatString(String measurement) {
        switch (measurement) {
            case "TEMPERATURE": return TEMPERATURE;
            case "HUMIDITY" : return HUMIDITY;
            case "SOUND": return SOUND;
            case "CO2": return CO2;
            default: return null;
        }
    }

    public String toUnit() {
        switch (type) {
            case "TEMPERATURE": return "°C";
            case "HUMIDITY": return "%";
            case "SOUND": return "dB";
            case "CO2": return "ppm";
        }
        return "";
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
