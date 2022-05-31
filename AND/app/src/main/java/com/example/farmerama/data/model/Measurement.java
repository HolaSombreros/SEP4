package com.example.farmerama.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.TypeConverters;

import com.example.farmerama.data.util.MeasurementConverter;

import java.time.LocalDateTime;

@Entity(tableName = "measurement_table", primaryKeys = {"measurementId", "measurementType"})
public class Measurement {
    @NonNull
    private int measurementId;
    private boolean latest;
    private int areaId;
    private double value;
    @TypeConverters(MeasurementConverter.class)
    private LocalDateTime measuredDate;
    @NonNull
    private MeasurementType measurementType;

    public Measurement(){}

    public Measurement(double value, LocalDateTime measuredDate, MeasurementType type) {
        this.value = value;
        this.measuredDate = measuredDate;
        this.measurementType = type;
    }

    public Measurement(int measurementId, int areaId, double value, LocalDateTime measuredDate, MeasurementType measurementType) {
        this.measurementId = measurementId;
        this.value = value;
        this.areaId = areaId;
        this.measuredDate = measuredDate;
        this.measurementType = measurementType;
    }

    public Measurement(int measurementId, boolean latest, int areaId, double value, LocalDateTime measuredDate, MeasurementType measurementType) {
        this.measurementId = measurementId;
        this.latest = latest;
        this.areaId = areaId;
        this.value = value;
        this.measuredDate = measuredDate;
        this.measurementType = measurementType;
    }

    public LocalDateTime getMeasuredDate() {
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

    public LocalDateTime getDateTime() {
        return measuredDate;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.measuredDate = dateTime;
    }

    public MeasurementType getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(MeasurementType measurementType) {
        this.measurementType = measurementType;
    }

    public void setMeasuredDate(LocalDateTime measuredDate) {
        this.measuredDate = measuredDate;
    }

}
