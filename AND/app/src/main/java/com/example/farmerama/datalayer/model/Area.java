package com.example.farmerama.datalayer.model;


public class Area {

    private Barn barn;
    private int id;
    private String name;
    private String description;
    private int numberOfPigs;

    public Area(String name, String description, int noOfPigs) {
        this.name = name;
        this.description = description;
        this.numberOfPigs = noOfPigs;
    }

    public Area(Barn barn, int id, String name, String description, int numberOfPigs) {
        this.barn = barn;
        this.id = id;
        this.name = name;
        this.description = description;
        this.numberOfPigs = numberOfPigs;
    }

    public Area(int barnId, int id, String name, String description, int noOfPigs) {
        barnId = barnId;
        this.id = id;
        this.name = name;
        this.description = description;
        this.numberOfPigs = noOfPigs;
    }

    public Barn getBarn() {
        return barn;
    }

    public void setBarn(Barn barn) {
        this.barn = barn;
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
}
