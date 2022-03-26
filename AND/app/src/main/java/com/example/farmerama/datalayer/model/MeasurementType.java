package com.example.farmerama.datalayer.model;

import androidx.annotation.NonNull;

public enum MeasurementType {
    TEMPERATURE
    private String role;

    Role(String name) {
        this.role = role;
    }

    @NonNull
    public String toString() {
        return role;
    }
}
