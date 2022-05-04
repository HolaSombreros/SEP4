package com.example.farmerama.datalayer.model.response;

import com.example.farmerama.datalayer.model.Area;

public class AreaResponse {
    private BarnResponse barn;
    private int id;
    private String name;
    private String description;
    private int numberOfPigs;

    public Area getArea(){
        return new Area(barn.getBarn(), id, name, description, numberOfPigs);
    }
}
