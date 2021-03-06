package com.example.farmerama.data.model.response;

import com.example.farmerama.data.model.ThresholdLogType;
import com.example.farmerama.data.model.ThresholdModification;

import java.time.LocalDate;

import java.time.LocalDateTime;

public class ThresholdModificationsResponse {

    private int logId;
    private ThresholdResponse threshold;
    private UserResponse user;
    private String changedOn;
    private double oldValue;
    private double newValue;
    private ThresholdLogType type;

    public ThresholdModification getModification() {
        LocalDate dateTime = LocalDateTime.parse(changedOn).toLocalDate();
        return new ThresholdModification(logId, threshold.getThreshold(), user.getUser(), dateTime, oldValue, newValue, type);
    }
}
