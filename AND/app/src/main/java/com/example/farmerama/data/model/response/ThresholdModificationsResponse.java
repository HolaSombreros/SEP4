package com.example.farmerama.data.model.response;

import com.example.farmerama.data.model.Threshold;
import com.example.farmerama.data.model.ThresholdLogType;
import com.example.farmerama.data.model.ThresholdModification;
import com.example.farmerama.data.model.User;

import java.time.LocalDateTime;

public class ThresholdModificationsResponse {

    private int logId;
    private Threshold threshold;
    private User user;
    private String changedOn;
    private double oldValue;
    private double newValue;
    private ThresholdLogType type;

    public ThresholdModification getModification() {
        LocalDateTime dateTime = LocalDateTime.parse(changedOn);
        return new ThresholdModification(logId, threshold, user, dateTime, oldValue, newValue, type);
    }
}
