package com.example.farmerama.datalayer.model;

import androidx.annotation.NonNull;

public enum Role {
    EMPLOYEE("Employee"), OWNER("Owner");
    private String role;

    Role(String role) {
        this.role = role;
    }

    @NonNull
    public String toString() {
        return role;
    }
}
