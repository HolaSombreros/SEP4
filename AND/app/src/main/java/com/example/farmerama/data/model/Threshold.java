package com.example.farmerama.data.model;

import java.util.Objects;

public class Threshold {
    private int id;
    private int areaId;
    private double maximum;
    private double minimum;
    private String type;

    public Threshold(int id, int areaId, double maximum, double minimum, String type) {
        this.minimum = minimum;
        this.maximum = maximum;
        this.areaId = areaId;
        this.id = id;
        this.type = type;
    }

    public Threshold(double minimum, double maximum) {
        this.maximum = maximum;
        this.minimum = minimum;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
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
                "id=" + id +
                ", areaId=" + areaId +
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
        return id == threshold.id && areaId == threshold.areaId && Double.compare(threshold.maximum, maximum) == 0 && Double.compare(threshold.minimum, minimum) == 0 && Objects.equals(type, threshold.type);
    }

}
