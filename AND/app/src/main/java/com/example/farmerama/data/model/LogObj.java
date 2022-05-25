package com.example.farmerama.data.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Entity(tableName = "measurement_log_table")
public class LogObj {
    @PrimaryKey
    private int logId;
    private MeasurementType measurementType;
    private String areaName;
    private String measuredDate;
    private double thresholdValue;
    private double exceededValue;

    public LogObj(){}

    public LogObj(int logId, MeasurementType measurementType, String measuredDate, double thresholdValue, double exceededValue) {
        this.logId = logId;
        this.measurementType = measurementType;
        this.measuredDate = measuredDate;
        this.thresholdValue = thresholdValue;
        this.exceededValue = exceededValue;
    }

    public LogObj(MeasurementType measurementType, String measuredDate, double thresholdValue, double exceededValue) {
        this.measurementType = measurementType;
        setMeasuredDate(measuredDate);
        this.thresholdValue = thresholdValue;
        this.exceededValue = exceededValue;
    }

    public LogObj(String areaName, MeasurementType type) {
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
        LocalDateTime datetime =LocalDateTime.parse(measuredDate);
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = datetime.format(myFormatObj);
        this.measuredDate = formattedDate;
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
