package com.dai.shared;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "threshold_logs")
public class ThresholdLogs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private int logId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "threshold_id")
    private Threshold threshold;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "changed_on")
    private LocalDateTime changedOn;

    @Column(name = "old_value")
    private double oldValue;
    @Column(name = "new_value")
    private double newValue;

    @Column(name = "type")
    private ThresholdLogType type;

    public ThresholdLogs() {
    }

    public ThresholdLogs(int logId, Threshold threshold, User user, LocalDateTime changedOn, double oldValue, double newValue, ThresholdLogType type) {
        this.logId = logId;
        this.threshold = threshold;
        this.user = user;
        this.changedOn = changedOn;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.type = type;
    }

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
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
