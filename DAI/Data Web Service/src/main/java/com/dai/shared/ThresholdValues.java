package com.dai.shared;

public class ThresholdValues {
    private double minimum;
    private double maximum;

    public ThresholdValues(double minimum, double maximum) {
        this.minimum = minimum;
        this.maximum = maximum;
    }

    public double getMinimum() {
        return minimum;
    }

    public void setMinimum(double minimum) {
        this.minimum = minimum;
    }

    public double getMaximum() {
        return maximum;
    }

    public void setMaximum(double maximum) {
        this.maximum = maximum;
    }
}
