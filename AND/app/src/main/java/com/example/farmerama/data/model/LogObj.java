package com.example.farmerama.data.model;

public class LogObj {
    private int id;
    private MeasurementType measurementType;
    private String measuredDate;
    private double thresholdValue;
    private double exceededValue;

    public LogObj(int id, MeasurementType measurementType, String measuredDate, double thresholdValue, double exceededValue) {
        this.id = id;
        this.measurementType = measurementType;
        this.measuredDate = measuredDate;
        this.thresholdValue = thresholdValue;
        this.exceededValue = exceededValue;
    }

    public LogObj(MeasurementType measurementType, String measuredDate, double thresholdValue, double exceededValue) {
        this.measurementType = measurementType;
        this.measuredDate = measuredDate;
        this.thresholdValue = thresholdValue;
        this.exceededValue = exceededValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MeasurementType getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(MeasurementType measurementType) {
        this.measurementType = measurementType;
    }

    public String getMeasuredDate() {
        return measuredDate;
    }

    public void setMeasuredDate(String measuredDate) {
        this.measuredDate = measuredDate;
    }

    public double getThresholdValue() {
        return thresholdValue;
    }

    public void setThresholdValue(double thresholdValue) {
        this.thresholdValue = thresholdValue;
    }

    public double getExceededValue() {
        return exceededValue;
    }

    public void setExceededValue(double exceededValue) {
        this.exceededValue = exceededValue;
    }
}
