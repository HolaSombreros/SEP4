package com.example.farmerama.datalayer.model;


public class Area {

    private Barn barn;
    private int id;
    private String name;
    private String description;
    private int numberOfPigs;
    private String hardwareId;

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
}
