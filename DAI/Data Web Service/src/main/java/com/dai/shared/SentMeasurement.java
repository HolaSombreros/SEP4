package com.dai.shared;

import java.time.LocalDateTime;

public class SentMeasurement {

    private LocalDateTime measuredDate;
    private double value;

    public SentMeasurement() {
    }

    public SentMeasurement(LocalDateTime measuredDate, double value) {
        this.measuredDate = measuredDate;
        this.value = value;
    }

    public LocalDateTime getMeasuredDate() {
        return measuredDate;
    }

    public void setMeasuredDate(LocalDateTime measuredDate) {
        this.measuredDate = measuredDate;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }


}
