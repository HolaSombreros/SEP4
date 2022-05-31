package com.example.farmerama.data.model.response;

import com.example.farmerama.data.model.ExceededLog;
import com.example.farmerama.data.model.MeasurementType;
import java.time.LocalDateTime;


public class LogResponse {
    private String measuredDate;
    private int measurementId;
    private String areaName;
    private MeasurementType type;
    private double threshold;
    private double value;

    public ExceededLog getLog(MeasurementType type) {
        LocalDateTime dateTime = LocalDateTime.parse(measuredDate);
        return new ExceededLog(measurementId,type, dateTime, threshold, value);
    }

    public ExceededLog getLog() {
        return new ExceededLog(areaName, type);
    }

}
