package com.example.farmerama.data.model;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "area_table")
public class Area {
    @Embedded
    private Barn barn;
    @PrimaryKey
    private int id;
    private String name;
    private String description;
    private int numberOfPigs;
    private String hardwareId;

    public Area(){}

    public Area(Barn barn, String name, String description, int numberOfPigs, String hardwareId) {
        this.barn = barn;
        this.name = name;
        this.description = description;
        this.numberOfPigs = numberOfPigs;
        this.hardwareId = hardwareId;
    }

    public Area(int id, Barn barn, String name, String description, int noOfPigs, String hardwareId) {
        this.barn = barn;
        this.id = id;
        this.name = name;
        this.description = description;
        this.numberOfPigs = noOfPigs;
        this.hardwareId = hardwareId;
    }

    public Barn getBarn() {
        return barn;
    }

    public void setBarnId(Barn barn) {
        barn = barn;
    }

    public int getNumberOfPigs() {
        return numberOfPigs;
    }

    public void setNumberOfPigs(int numberOfPigs) {
        this.numberOfPigs = numberOfPigs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getNoOfPigs() {
        return numberOfPigs;
    }

    public void setNoOfPigs(int noOfPigs) {
        this.numberOfPigs = noOfPigs;
    }

    public String getHardwareId() {
        return hardwareId;
    }

    public void setHardwareId(String hardwareId) {
        this.hardwareId = hardwareId;
    }

    public void setBarn(Barn barn) {
        this.barn = barn;
    }
}
