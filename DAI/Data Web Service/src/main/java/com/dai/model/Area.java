package com.dai.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "area")
public class Area implements Serializable{
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "area_id")
    private int areaId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="barn_id")
    private Barn barn;

    @Column(name = "name")
    @NotNull(message = "Please fill in all the required fields")
    @NotEmpty(message = "Please fill in all the required fields")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "number_of_pigs")
    private int numberOfPigs;

    @Column(name = "hardware_id")
    @NotNull(message = "Please fill in all the required fields")
    @NotEmpty(message = "Please fill in all the required fields")
    private String hardwareId;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "area_id")
    private List<Measurement> measurements;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "area_id")
    private List<Threshold> thresholds;

    public Area() {
    }

    public Area(int id, Barn barn, String name, String description, int numberOfPigs, String hardwareId) {
        this.areaId = id;
        this.barn = barn;
        this.name = name;
        this.description = description;
        this.numberOfPigs = numberOfPigs;
        this.hardwareId = hardwareId;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public Barn getBarn() {
        return barn;
    }

    public void setBarn(Barn barn) {
        this.barn = barn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberOfPigs() {
        return numberOfPigs;
    }

    public void setNumberOfPigs(int numberOfPigs) {
        this.numberOfPigs = numberOfPigs;
    }

    public String getHardwareId() {
        return hardwareId;
    }

    public void setHardwareId(String hardwareId) {
        this.hardwareId = hardwareId;
    }
}
