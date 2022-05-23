package com.example.farmerama.data.model.response;

import com.example.farmerama.data.model.Threshold;
import com.example.farmerama.data.model.ThresholdLogType;
import com.example.farmerama.data.model.ThresholdModifications;
import com.example.farmerama.data.model.User;

import java.time.LocalDateTime;

public class ThresholdModificationsResponse {

    // TODO remove database names
    private int log_id;
    private Threshold threshold_id;
    private User user_id;
    private String changedOn;
    private double old_value;
    private double new_value;
    private ThresholdLogType type;

    public ThresholdModifications getModification() {
        LocalDateTime dateTime = LocalDateTime.parse(changedOn);

        return new ThresholdModifications(log_id, threshold_id, user_id, dateTime, old_value, new_value, type);
    }
}
