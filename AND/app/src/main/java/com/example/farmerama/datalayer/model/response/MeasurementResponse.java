package com.example.farmerama.datalayer.model.response;

import com.example.farmerama.datalayer.model.Measurement;
import com.example.farmerama.datalayer.model.MeasurementType;

public class MeasurementResponse {
    private double value;
    private String measuredDate;

    public Measurement getMeasurement(MeasurementType type) {
        return new Measurement(value, measuredDate, type);
    }
}
