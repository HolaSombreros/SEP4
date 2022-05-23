package com.example.farmerama.data.model.response;

import com.example.farmerama.data.model.Threshold;

public class ThresholdResponse {
    private double minimum;
    private double maximum;

    public Threshold getThreshold() {
        return new Threshold(minimum, maximum);
    }
}
