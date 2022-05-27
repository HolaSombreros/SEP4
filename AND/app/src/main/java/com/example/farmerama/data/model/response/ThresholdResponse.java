package com.example.farmerama.data.model.response;

import com.example.farmerama.data.model.Threshold;

public class ThresholdResponse {
    private double minimum;
    private double maximum;
    private int thresholdId;
    private String type;
    private AreaResponse area;


    public Threshold getThreshold() {
        return new Threshold(thresholdId, area.getArea(), minimum, maximum, type);
    }
}
