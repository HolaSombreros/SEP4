package com.example.farmerama.data.model.response;

import com.example.farmerama.data.model.LogObj;
import com.example.farmerama.data.model.MeasurementType;


public class LogResponse {
    private String measuredDate;
    private double threshold;
    private double value;

    public LogObj getLog(MeasurementType type){
        return new LogObj(type, measuredDate, threshold,value  );
    }

}
