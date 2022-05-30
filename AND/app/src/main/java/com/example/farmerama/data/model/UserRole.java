package com.example.farmerama.data.model;

import androidx.room.TypeConverters;

@TypeConverters
public enum UserRole {
    ADMINISTRATOR("ADMINISTRATOR"),
    EMPLOYEE("EMPLOYEE"),
    OFFLINE("OFFLINE");

    String role;

    UserRole(String role) {
        this.role = role;
    }

    public void setRole(String role) {
        switch (role) {
            case "ADMINISTRATOR": role = ADMINISTRATOR.getRole();
            break;
            default: role = EMPLOYEE.getRole();
        }
    }
    public String getRole() {
        return role;
    }

}
