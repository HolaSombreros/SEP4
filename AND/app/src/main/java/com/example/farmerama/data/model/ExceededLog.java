package com.example.farmerama.data.model;

import androidx.room.Entity;

import androidx.annotation.NonNull;

import java.time.LocalDateTime;

@Entity(tableName = "exceeded_log_table", primaryKeys = {"logId", "measurementType"})
public class ExceededLog {
    @NonNull
    private int logId;
    @NonNull
    private MeasurementType measurementType;
    private String areaName;
    private LocalDateTime measuredDate;
    private double thresholdValue;
    private double exceededValue;

    public ExceededLog(){}

    public ExceededLog(int logId, MeasurementType measurementType, LocalDateTime measuredDate, double thresholdValue, double exceededValue) {
        this.logId = logId;
        this.measurementType = measurementType;
        this.measuredDate = measuredDate;
        this.thresholdValue = thresholdValue;
        this.exceededValue = exceededValue;
    }

    public ExceededLog(MeasurementType measurementType, LocalDateTime measuredDate, double thresholdValue, double exceededValue) {
        this.measurementType = measurementType;
        this.measuredDate = measuredDate;
        this.thresholdValue = thresholdValue;
        this.exceededValue = exceededValue;
    }

    public ExceededLog(String areaName, MeasurementType type) {
        this.measurementType = type;
        this.areaName = areaName;
    }

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public MeasurementType getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(MeasurementType measurementType) {
        this.measurementType = measurementType;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public LocalDateTime getMeasuredDate() {
        return measuredDate;
    }

    public void setMeasuredDate(LocalDateTime measuredDate) {
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
