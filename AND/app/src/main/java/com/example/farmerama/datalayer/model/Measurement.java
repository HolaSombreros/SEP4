package com.example.farmerama.datalayer.model;

import java.time.LocalDateTime;

public class Measurement {

    private int id;
    private double value;
    private LocalDateTime dateTime;
    private UnitOfMeasurement unitOfMeasurement;
    private MeasurementType measurementType;

    public Measurement(int id, double value, LocalDateTime dateTime, UnitOfMeasurement unitOfMeasurement, MeasurementType measurementType) {
        this.id = id;
        this.value = value;
        this.dateTime = dateTime;
        this.unitOfMeasurement = unitOfMeasurement;
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

    public UnitOfMeasurement getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(UnitOfMeasurement unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public MeasurementType getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(MeasurementType measurementType) {
        this.measurementType = measurementType;
    }
}
