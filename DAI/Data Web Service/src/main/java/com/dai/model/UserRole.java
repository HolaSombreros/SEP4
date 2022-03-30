package com.dai.model;

import java.security.SecureRandom;

public enum UserRole {
    ADMINISTRATOR("administrator"),
    EMPLOYEE("employee");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
