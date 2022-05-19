package com.example.farmerama.data.model.response;

import com.example.farmerama.data.model.Barn;

public class BarnResponse {
    private int id;
    private String name;

    public Barn getBarn() {
        return new Barn(id, name);
    }
}
