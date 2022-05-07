package com.example.farmerama.datalayer.model.response;

import com.example.farmerama.datalayer.model.Area;

import java.util.ArrayList;
import java.util.List;

public class AreaResponse {
    private int barn;
    private int id;
    private String name;
    private String description;
    private int numberOfPigs;
    private String hardwareId;


    public Area getArea(){
        return new Area(id, barn, name, description,numberOfPigs, hardwareId);
    }
}
