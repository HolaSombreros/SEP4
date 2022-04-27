package com.example.farmerama.datalayer.model;

import java.time.LocalDateTime;

public class Measurement {

    private int id;
    private double value;
    private LocalDateTime dateTime;
    private MeasurementType measurementType;
    private double temperature;
    private double humidity;

    public Measurement(int id, double value, LocalDateTime dateTime, MeasurementType measurementType) {
        this.id = id;
        this.value = value;
        this.dateTime = dateTime;
        this.measurementType = measurementType;
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
