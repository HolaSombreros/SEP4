package com.example.farmerama.data.model;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

@Entity(tableName = "threshold_modifications_table")
public class ThresholdModifications {
    @PrimaryKey
    private int modificationId;
    @Embedded
    private Threshold threshold;
    @Embedded
    private User user;
    private LocalDateTime changedOn;
    private double oldValue;
    private double newValue;
    private ThresholdLogType type;

    public ThresholdModifications(){}

    public ThresholdModifications(int modificationId, Threshold threshold, User user, LocalDateTime changedOn, double oldValue, double newValue, ThresholdLogType type) {
        this.modificationId = modificationId;
        this.threshold = threshold;
        this.user = user;
        this.changedOn = changedOn;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.type = type;
    }

    public int getModificationId() {
        return modificationId;
    }

    public void setModificationId(int modificationId) {
        this.modificationId = modificationId;
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
