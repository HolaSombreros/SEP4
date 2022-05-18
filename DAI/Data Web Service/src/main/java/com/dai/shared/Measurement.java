package com.dai.shared;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "measurement")
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "measurement_id")
    private int id;
    @Column(name = "measured_date")
    private LocalDateTime measuredDate;
    @Column(name = "temperature")
    private double temperature;
    @Column(name = "humidity")
    private double humidity;
    @Column(name = "co2")
    private int co2 = -1000;
    @Column(name = "sound")
    private double sound = -1000;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id")
    private Area area;


    protected Measurement() {
    }

    public Measurement(int id, LocalDateTime measuredDate, double temperature, double humidity, int co2, double sound, Area area) {
        this.id = id;
        this.measuredDate = measuredDate;
        this.temperature = temperature;
        this.humidity = humidity;
        this.co2 = co2;
        this.sound = sound;
        this.area = area;
    }

    public Measurement(LocalDateTime measuredDate, double temperature, double humidity, int co2, double sound, Area area) {
        this.measuredDate = measuredDate;
        this.temperature = temperature;
        this.humidity = humidity;
        this.co2 = co2;
        this.sound = sound;
        this.area = area;
    }

    public Measurement(LocalDateTime measuredDate, Area area) {
        this.measuredDate = measuredDate;
        this.area = area;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getMeasuredDate() {
        return measuredDate;
    }

    public void setMeasuredDate(LocalDateTime measuredDate) {
        this.measuredDate = measuredDate;
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

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "Date: " + measuredDate.format(DateTimeFormatter.ISO_DATE_TIME) + ", temp: " + temperature + ", humidity: " + humidity + ", CO2: " + co2 + ", sound: " + sound;
    }
}

