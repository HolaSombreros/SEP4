package com.dai.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "measurement")
public class Measurement {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "measurement_id")
    private int id;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type")
    private MeasurementType type;
    @Column(name = "value")
    private double value;
    @Column(name = "measured_date")
    private LocalDateTime measuredDate;
    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "hardware_id")
    private Hardware hardware;


    protected Measurement() {
    }

    public Measurement(MeasurementType type, double value, LocalDateTime measuredDate, Hardware hardware) {
        this.type = type;
        this.value = value;
        this.measuredDate = measuredDate;
        this.hardware = hardware;
    }

    public int getId() {
        return id;
    }

    public MeasurementType getType() {
        return type;
    }

    public double getValue() {
        return value;
    }

    public LocalDateTime getMeasuredDate() {
        return measuredDate;
    }

    public Hardware getHardware() {
        return hardware;
    }
}

