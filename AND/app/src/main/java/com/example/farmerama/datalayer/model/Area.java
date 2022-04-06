package com.example.farmerama.datalayer.model;


public class Area {

    private int barnId;
    private int id;
    private String name;
    private String description;
    private int noOfPigs;

    public Area(int barnId,int id, String name, String description, int noOfPigs) {
        barnId = barnId;
        this.id = id;
        this.name = name;
        this.description = description;
        this.noOfPigs = noOfPigs;
    }

    public int getBarnId() {
        return barnId;
    }

    public void setBarnId(int barnId) {
        this.barnId = barnId;
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
        return noOfPigs;
    }

    public void setNoOfPigs(int noOfPigs) {
        this.noOfPigs = noOfPigs;
    }
}
