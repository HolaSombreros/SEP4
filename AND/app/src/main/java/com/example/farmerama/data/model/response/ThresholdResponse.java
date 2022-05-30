package com.example.farmerama.data.model.response;

import com.example.farmerama.data.model.MeasurementType;
import com.example.farmerama.data.model.Threshold;

public class ThresholdResponse {
    private double minimum;
    private double maximum;
    private int thresholdId;
    private MeasurementType type;
    private AreaResponse area;

    public Threshold getThreshold() {
        return new Threshold(thresholdId, area.getArea(), maximum, minimum, type);
    }
}
