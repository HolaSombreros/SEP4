package com.dai.shared;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "hardware_id")
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

    public void setId(int id) {
        this.id = id;
    }

    public void setType(MeasurementType type) {
        this.type = type;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setMeasuredDate(LocalDateTime measuredDate) {
        this.measuredDate = measuredDate;
    }

    public void setHardware(Hardware hardware) {
        this.hardware = hardware;
    }
}

