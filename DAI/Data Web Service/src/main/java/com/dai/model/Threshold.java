package com.dai.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "threshold")
public class Threshold {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "threshold_id")
    private int thresholdId;

    @Column(name = "minimum")
    private double minimum;

    @Column(name = "maximum")
    private double maximum;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ThresholdType type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "area_id")
    private Area area;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "threshold_id")
    private List<ThresholdLogs> thresholdLogs;

    protected Threshold() {
    }


    public Threshold(int id, double minimum, double maximum, ThresholdType type, Area area) {
        this.thresholdId = id;
        this.minimum = minimum;
        this.maximum = maximum;
        this.type = type;
        this.area = area;
    }

    public int getThresholdId() {
        return thresholdId;
    }

    public void setThresholdId(int thresholdId) {
        this.thresholdId = thresholdId;
    }

    public double getMinimum() {
        return minimum;
    }

    public void setMinimum(double minimum) {
        this.minimum = minimum;
    }

    public double getMaximum() {
        return maximum;
    }

    public void setMaximum(double maximum) {
        this.maximum = maximum;
    }

    public ThresholdType getType() {
        return type;
    }

    public void setType(ThresholdType type) {
        this.type = type;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Area getArea() {
        return area;
    }

    public ThresholdValues toThresholdValues() {
        return new ThresholdValues(minimum, maximum);
    }
}
