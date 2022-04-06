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
    @Column(name = "measured_date")
    private LocalDateTime measuredDate;
    @Column(name = "temperature")
    private double temperature;
    @Column(name = "humidity")
    private double humidity;
    @Column(name = "co2")
    private int co2;
    @Column(name = "sound")
    private double sound;
    @ManyToOne(fetch = FetchType.LAZY)
    private Hardware hardware;


    protected Measurement() {
    }

    public Measurement(LocalDateTime measuredDate, double temperature, double humidity, int co2, double sound, Hardware hardware) {
        this.measuredDate = measuredDate;
        this.temperature = temperature;
        this.humidity = humidity;
        this.co2 = co2;
        this.sound = sound;
        this.hardware = hardware;
    }

    public int getId() {
        return id;
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

    public void setMeasuredDate(LocalDateTime measuredDate) {
        this.measuredDate = measuredDate;
    }

    public void setHardware(Hardware hardware) {
        this.hardware = hardware;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public int getCo2() {
        return co2;
    }

    public void setCo2(int co2) {
        this.co2 = co2;
    }

    public double getSound() {
        return sound;
    }

    public void setSound(double sound) {
        this.sound = sound;
    }
}

