package com.example.farmerama.data.model.response;

import com.example.farmerama.data.model.Area;

public class AreaResponse {
    private int id;
    private BarnResponse barn;
    private String name;
    private String description;
    private int numberOfPigs;
    private String hardwareId;
    
    public Area getArea(){
        return new Area(id, barn.getBarn(), name, description,numberOfPigs, hardwareId);
    }
}
