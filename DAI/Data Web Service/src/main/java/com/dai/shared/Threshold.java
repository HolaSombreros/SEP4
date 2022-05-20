package com.dai.shared;

import javax.persistence.*;

@Entity
@Table(name = "threshold")
public class Threshold {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "threshold_id")
    private int id;

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

    protected Threshold() {
    }


    public Threshold(int id, double minimum, double maximum, ThresholdType type, Area area) {
        this.id = id;
        this.minimum = minimum;
        this.maximum = maximum;
        this.type = type;
        this.area = area;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
