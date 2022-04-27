package com.example.farmerama.datalayer.model;

import androidx.annotation.NonNull;

public enum MeasurementType {
    TEMPERATURE("Temperature"), HUMIDITY("Humidity");
    private String type;

    MeasurementType(String type) {
        this.type = type;
    }

    @NonNull
    public String toString() {
        return type;
    }
}
