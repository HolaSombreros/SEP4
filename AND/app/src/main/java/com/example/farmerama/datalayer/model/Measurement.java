package com.example.farmerama.datalayer.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Measurement {

    private int id;
    private double value;
    private String dateTime;
    private MeasurementType measurementType;

    public Measurement(int id, double value, String dateTime, MeasurementType measurementType) {
        this.id = id;
        this.value = value;
        this.dateTime = dateTime;
        this.measurementType = measurementType;
    }
    public Measurement(double value, String measuredDate, MeasurementType type) {
        this.value = value;
        this.dateTime = measuredDate;
        this.measurementType = type;
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

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public MeasurementType getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(MeasurementType measurementType) {
        this.measurementType = measurementType;
    }
}
