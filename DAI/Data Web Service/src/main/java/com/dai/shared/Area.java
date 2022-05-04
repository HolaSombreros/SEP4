package com.dai.shared;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Entity
@Table(name = "area")
public class Area implements Serializable{
    
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "area_id")
    private int id;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="barn_id")
    private Barn barn;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "number_of_pigs")
    private int numberOfPigs;
    @Column(name = "hardware_id")
    private int hardwareId;

    protected Area() {
    }

    public Area(int id, Barn barn, String name, String description, int numberOfPigs, int hardwareId) {
        this.id = id;
        this.barn = barn;
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

    public int getHardwareId() {
        return hardwareId;
    }

    public void setHardwareId(int hardwareId) {
        this.hardwareId = hardwareId;
    }
}
