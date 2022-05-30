package com.example.farmerama.data.model;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.farmerama.data.util.Converters;

import java.time.LocalDateTime;

@Entity(tableName = "threshold_modifications_table")
public class ThresholdModification {
    @PrimaryKey
    private int modificationId;
    @Embedded
    private Threshold threshold;
    @Embedded
    private User user;
    @TypeConverters(Converters.class)
    private LocalDateTime changedOn;
    private double oldValue;
    private double newValue;
    @NonNull
    private ThresholdLogType logType;

    public ThresholdModification(){}

    public ThresholdModification(int modificationId, Threshold threshold, User user, LocalDateTime changedOn, double oldValue, double newValue, ThresholdLogType type) {
        this.modificationId = modificationId;
        this.threshold = threshold;
        this.user = user;
        this.changedOn = changedOn;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.logType = type;
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

    public ThresholdLogType getLogType() {
        return logType;
    }

    public void setLogType(ThresholdLogType logType) {
        this.logType = logType;
    }
}
