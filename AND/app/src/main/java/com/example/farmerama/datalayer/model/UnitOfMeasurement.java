package com.example.farmerama.datalayer.model;

import androidx.annotation.NonNull;

public enum UnitOfMeasurement {
    CELSIUS("Celsius");
    private String unit;

    UnitOfMeasurement(String unit) {
        this.unit = unit;
    }

    @NonNull
    public String toString() {
        return unit;
    }
}
