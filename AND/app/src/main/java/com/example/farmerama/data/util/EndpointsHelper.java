package com.example.farmerama.data.util;

import androidx.annotation.NonNull;

public enum EndpointsHelper {
    AREAS("areas/"), THRESHOLDS("thresholds/"), USERS("users/"), BARNS("barns/");

    private String type;

    EndpointsHelper(String type) {
        this.type = type;
    }

    @NonNull
    public String toString() {
        return type;
    }

}
