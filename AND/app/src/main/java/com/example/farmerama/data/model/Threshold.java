package com.example.farmerama.data.model;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "threshold_table")
public class Threshold {
    @PrimaryKey
    private int thresholdId;
    @Embedded(prefix = "area")
    private Area area;
    private double maximum;
    private double minimum;
    private String type;

    public Threshold(){}

    public Threshold(int thresholdId, Area area, double maximum, double minimum, String type) {
        this.minimum = minimum;
        this.maximum = maximum;
        this.area = area;
        this.thresholdId = thresholdId;
        this.type = type;
    }

    public Threshold(double minimum, double maximum) {
        this.maximum = maximum;
        this.minimum = minimum;
    }

    public int getThresholdId() {
        return thresholdId;
    }

    public void setThresholdId(int thresholdId) {
        this.thresholdId = thresholdId;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public double getMaximum() {
        return maximum;
    }

    public void setMaximum(double maximum) {
        this.maximum = maximum;
    }

    public double getMinimum() {
        return minimum;
    }

    public void setMinimum(double minimum) {
        this.minimum = minimum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Threshold{" +
                "id=" + thresholdId +
                ", areaId=" + area.getAreaId() +
                ", maximum=" + maximum +
                ", minimum=" + minimum +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Threshold threshold = (Threshold) o;
        return thresholdId == threshold.thresholdId && area.equals(threshold.area) && Double.compare(threshold.maximum, maximum) == 0 && Double.compare(threshold.minimum, minimum) == 0 && Objects.equals(type, threshold.type);
    }

}
