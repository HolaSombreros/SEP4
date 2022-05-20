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
}
