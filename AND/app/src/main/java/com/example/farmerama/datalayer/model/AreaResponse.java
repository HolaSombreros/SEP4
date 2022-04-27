package com.example.farmerama.datalayer.model;

public class AreaResponse {
    private int barnId;
    private int id;
    private String name;
    private String description;
    private int numberOfPigs;

    public Area getArea(){
        return new Area(barnId, id, name, description, numberOfPigs);
    }
}
