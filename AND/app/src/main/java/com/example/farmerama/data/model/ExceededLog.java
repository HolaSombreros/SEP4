package com.example.farmerama.data.model;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.farmerama.data.util.DateFormatter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Entity(tableName = "exceeded_log_table", primaryKeys = {"logId", "measurementType"})
public class ExceededLog {
    @NonNull
    private int logId;
    @NonNull
    private MeasurementType measurementType;
    private String areaName;
    private String measuredDate;
    private double thresholdValue;
    private double exceededValue;

    public ExceededLog(){}

    public ExceededLog(int logId, MeasurementType measurementType, String measuredDate, double thresholdValue, double exceededValue) {
        this.logId = logId;
        this.measurementType = measurementType;
        this.measuredDate = DateFormatter.formatDate(measuredDate);
        this.thresholdValue = thresholdValue;
        this.exceededValue = exceededValue;
    }

    public ExceededLog(MeasurementType measurementType, String measuredDate, double thresholdValue, double exceededValue) {
        this.measurementType = measurementType;
        this.measuredDate = DateFormatter.formatDate(measuredDate);
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
