package com.dai.shared;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "threshold_logs")
public class ThresholdLogs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private int log_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "threshold_id")
    private Threshold threshold_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user_id;

    @Column(name = "changed_on")
    private LocalDateTime changed_on;

    @Column(name = "old_value")

    private double old_value;
    @Column(name = "new_value")

    private double new_value;
    @Column(name = "type")
    private ThresholdType type;

    public ThresholdLogs() {
    }

    public int getLog_id() {
        return log_id;
    }

    public void setLog_id(int log_id) {
        this.log_id = log_id;
    }

    public Threshold getThreshold_id() {
        return threshold_id;
    }

    public void setThreshold_id(Threshold threshold_id) {
        this.threshold_id = threshold_id;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public LocalDateTime getChanged_on() {
        return changed_on;
    }

    public void setChanged_on(LocalDateTime changed_on) {
        this.changed_on = changed_on;
    }

    public double getOld_value() {
        return old_value;
    }

    public void setOld_value(double old_value) {
        this.old_value = old_value;
    }

    public double getNew_value() {
        return new_value;
    }

    public void setNew_value(double new_value) {
        this.new_value = new_value;
    }

    public ThresholdType getType() {
        return type;
    }

    public void setType(ThresholdType type) {
        this.type = type;
    }
}
