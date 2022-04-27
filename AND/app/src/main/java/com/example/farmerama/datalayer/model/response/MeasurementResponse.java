package com.example.farmerama.datalayer.model.response;

import com.example.farmerama.datalayer.model.Measurement;

public class MeasurementResponse {
    private double value;
    private String measuredDate;

    public Measurement getMeasurement() {
        return new Measurement(value, measuredDate);
    }
}
