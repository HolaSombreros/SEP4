package com.example.farmerama.datalayer.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Measurement {

    private int id;
    private double value;
    private String measuredDate;
    private MeasurementType measurementType;

    public Measurement(int id, double value, String dateTime, MeasurementType measurementType) {
        this.id = id;
        this.value = value;
        //this.measuredDate = dateTime;
        this.measurementType = measurementType;
    }
    public Measurement(double value, String measuredDate, MeasurementType type) {
        this.value = value;
        setMeasuredDate(measuredDate);
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
