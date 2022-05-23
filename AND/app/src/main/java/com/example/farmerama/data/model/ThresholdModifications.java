package com.example.farmerama.data.model;

import java.time.LocalDateTime;

public class ThresholdModifications {

    private int id;
    private Threshold threshold;
    private User user;
    private LocalDateTime changedOn;
    private double oldValue;
    private double newValue;
    private ThresholdLogType type;

    public ThresholdModifications(int id, Threshold threshold, User user, LocalDateTime changedOn, double oldValue, double newValue, ThresholdLogType type) {
        this.id = id;
        this.threshold = threshold;
        this.user = user;
        this.changedOn = changedOn;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Threshold getThreshold() {
        return threshold;
    }

    public void setThreshold(Threshold threshold) {
        this.threshold = threshold;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getChangedOn() {
        return changedOn;
    }

    public void setChangedOn(LocalDateTime changedOn) {
        this.changedOn = changedOn;
    }

    public double getOldValue() {
        return oldValue;
    }

    public void setOldValue(double oldValue) {
        this.oldValue = oldValue;
    }

    public double getNewValue() {
        return newValue;
    }

    public void setNewValue(double newValue) {
        this.newValue = newValue;
    }

    public ThresholdLogType getType() {
        return type;
    }

    public void setType(ThresholdLogType type) {
        this.type = type;
    }
}
