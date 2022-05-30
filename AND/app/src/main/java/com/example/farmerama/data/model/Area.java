package com.example.farmerama.data.model;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "area_table")
public class Area {
    @Embedded
    private Barn barnArea;
    @PrimaryKey
    private int areaId;
    private String areaName;
    private String description;
    private int numberOfPigs;
    private String hardwareId;

    public Area(){}

    public Area(Barn barn, String areaName, String description, int numberOfPigs, String hardwareId) {
        this.barnArea = barn;
        this.areaName = areaName;
        this.description = description;
        this.numberOfPigs = numberOfPigs;
        this.hardwareId = hardwareId;
    }

    public Area(int areaId, Barn barnArea, String areaName, String description, int noOfPigs, String hardwareId) {
        this.barnArea = barnArea;
        this.areaId = areaId;
        this.areaName = areaName;
        this.description = description;
        this.numberOfPigs = noOfPigs;
        this.hardwareId = hardwareId;
    }

    public Barn getBarnArea() {
        return barnArea;
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

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
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

    public void setBarnArea(Barn barnArea) {
        this.barnArea = barnArea;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Area area = (Area) o;
        return areaId == area.areaId && numberOfPigs == area.numberOfPigs && Objects.equals(barnArea, area.barnArea) && Objects.equals(areaName, area.areaName) && Objects.equals(description, area.description) && Objects.equals(hardwareId, area.hardwareId);
    }
}
