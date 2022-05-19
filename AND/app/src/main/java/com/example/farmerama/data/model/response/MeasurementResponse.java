package com.example.farmerama.data.model.response;

import com.example.farmerama.data.model.Measurement;
import com.example.farmerama.data.model.MeasurementType;

import java.util.ArrayList;
import java.util.List;

public class MeasurementResponse {
    private List<Measurement> measurements;
    private double value;
    private String measuredDate;

    public MeasurementResponse() {
        measurements = new ArrayList<>();
    }

    public Measurement getMeasurement(MeasurementType type) {
        return new Measurement(value, measuredDate, type);
    }

    public List<Measurement> getMeasurements(){
        return measurements;
    }
}
