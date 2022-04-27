package com.example.farmerama.datalayer.model;

public class MeasurementResponse {
    private double value;
    private String measuredDate;

    public Measurement getMeasurement() {
        return new Measurement(value, measuredDate);
    }
}
