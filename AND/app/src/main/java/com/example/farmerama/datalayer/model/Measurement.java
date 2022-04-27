package com.example.farmerama.datalayer.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Measurement {

    private int id;
    private double value;
    private LocalDateTime dateTime;
    private MeasurementType measurementType;

    public Measurement(int id, double value, LocalDateTime dateTime, MeasurementType measurementType) {
        this.id = id;
        this.value = value;
        this.dateTime = dateTime;
        this.measurementType = measurementType;
    }
    public Measurement(double value, String measuredDate) {
        this.value = value;
        //TODO: format date
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public MeasurementType getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(MeasurementType measurementType) {
        this.measurementType = measurementType;
    }
}
