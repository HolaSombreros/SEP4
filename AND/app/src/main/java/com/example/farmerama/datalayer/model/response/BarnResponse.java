package com.example.farmerama.datalayer.model.response;

import com.example.farmerama.datalayer.model.Barn;

public class BarnResponse {
    private int id;
    private String name;

    public Barn getBarn() {
        return  new Barn(id, name);
    }
}
