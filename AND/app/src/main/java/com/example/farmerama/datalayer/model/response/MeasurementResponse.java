package com.example.farmerama.datalayer.model.response;

import com.example.farmerama.datalayer.model.Measurement;
import com.example.farmerama.datalayer.model.MeasurementType;

import java.util.ArrayList;
import java.util.List;

public class MeasurementResponse {
    private List<Measurement> measurements;
    private double value;
    private String measuredDate;

    public MeasurementResponse() {
        measurements = new ArrayList<>();
    }

    public Measurement getMeasurement() {
        return new Measurement(value, measuredDate);

    public List<Measurement> getMeasurements(){
        return measurements;
    }
}
