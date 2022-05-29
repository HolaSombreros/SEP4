package com.example.farmerama.data.model.response;

import com.example.farmerama.data.model.ExceededLog;
import com.example.farmerama.data.model.MeasurementType;


public class LogResponse {
    private String measuredDate;
    private int measurementId;
    private String areaName;
    private MeasurementType type;
    private double threshold;
    private double value;

    public ExceededLog getLog(MeasurementType type) {
        return new ExceededLog(measurementId,type, measuredDate, threshold, value);
    }

    public ExceededLog getLog() {
        return new ExceededLog(areaName, type);
    }

}
