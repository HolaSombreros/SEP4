package com.example.farmerama.datalayer.model;

import java.util.List;

public class Area {

    private int id;
    private String name;
    private String description;
    private int noOfPigs;

    public Area(int id, String name, String description, int noOfPigs) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.noOfPigs = noOfPigs;
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
