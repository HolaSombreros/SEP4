package com.dai.shared;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "area")
public class Area implements Serializable{
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "area_id")
    private int id;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="barn_id")
    private Barn barn;
    @Column(name="barn_id")
    @Transient
    private int barnId;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "number_of_pigs")
    private int numberOfPigs;
    @Column(name = "hardware_id")
    private String hardwareId;

    protected Area() {
    }

    public Area(int id, Barn barn, String name, String description, int numberOfPigs, String hardwareId) {
        this.id = id;
        this.barn = barn;
        this.name = name;
        this.description = description;
        this.numberOfPigs = numberOfPigs;
        this.hardwareId = hardwareId;
    }

    public Area(int id, int barnId, String name, String description, int numberOfPigs, String hardwareId) {
        this.id = id;
        this.barnId = barnId;
        this.name = name;
        this.description = description;
        this.numberOfPigs = numberOfPigs;
        this.hardwareId = hardwareId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getBarnId() {
        return barnId;
    }

    public void setBarnId(int barnId) {
        this.barnId = barnId;
    }
}
