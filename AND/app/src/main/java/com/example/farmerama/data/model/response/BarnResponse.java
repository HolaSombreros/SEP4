package com.example.farmerama.data.model.response;

import com.example.farmerama.data.model.Barn;

public class BarnResponse {
    private int barnId;
    private String name;

    public Barn getBarn() {
        return new Barn(barnId, name);
    }
}
