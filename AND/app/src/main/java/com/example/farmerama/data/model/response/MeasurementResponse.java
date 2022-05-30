package com.example.farmerama.data.model.response;

import com.example.farmerama.data.model.Measurement;
import com.example.farmerama.data.model.MeasurementType;
import com.example.farmerama.data.util.DateFormatter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MeasurementResponse {
    private double value;
    private String measuredDate;
    private int measurementId;
    private int areaId;

    public Measurement getMeasurement(MeasurementType type) {
        LocalDateTime dateTime = LocalDateTime.parse(measuredDate);
        return new Measurement(measurementId,areaId, value, dateTime, type);
    }
}
