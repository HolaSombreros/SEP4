package com.example.farmerama.datalayer.model;

import androidx.annotation.NonNull;

public enum Role {
    GUEST("Guest"), EMPLOYEE("Employee"), OWNER("Owner");
    private String role;

    Role(String name) {
        this.role = role;
    }

    @NonNull
    public String toString() {
        return role;
    }
}
