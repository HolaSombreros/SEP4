package com.example.farmerama.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity(tableName = "measurement_table")
public class Measurement {
    @PrimaryKey
    private int measurementId;
    private boolean latest;
    private int areaId;
    private double value;
    private String measuredDate;
    private MeasurementType measurementType;

    public Measurement(){}

    public Measurement(int measurementId, double value, String dateTime, MeasurementType measurementType) {
        this.measurementId = measurementId;
        this.value = value;
        //this.measuredDate = dateTime;
        this.measurementType = measurementType;
    }
    public Measurement(double value, String measuredDate, MeasurementType type) {
        this.value = value;
        setMeasuredDate(measuredDate);
        this.measurementType = type;
    }

    public Measurement(int measurementId, boolean latest, int areaId, double value, String measuredDate, MeasurementType measurementType) {
        this.measurementId = measurementId;
        this.latest = latest;
        this.areaId = areaId;
        this.value = value;
        this.measuredDate = measuredDate;
        this.measurementType = measurementType;
    }

    public String getMeasuredDate() {
        return measuredDate;
    }

    public boolean isLatest() {
        return latest;
    }

    public void setLatest(boolean latest) {
        this.latest = latest;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public int getMeasurementId() {
        return measurementId;
    }

    public void setMeasurementId(int measurementId) {
        this.measurementId = measurementId;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getDateTime() {
        return measuredDate;
    }

    public void setDateTime(String dateTime) {
        this.measuredDate = dateTime;
    }

    public MeasurementType getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(MeasurementType measurementType) {
        this.measurementType = measurementType;
    }

    public void setMeasuredDate(String measuredDate) {
        LocalDateTime datetime =LocalDateTime.parse(measuredDate);
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = datetime.format(myFormatObj);
        this.measuredDate = formattedDate;
    }
}
