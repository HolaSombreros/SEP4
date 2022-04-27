package com.example.farmerama.datalayer.model;

public class UserResponse {
    private int id;
    private String name;
    private String email;
    private String password;
    private String role;

    public User getUser(){
        return new User(id, name, email, password, role);
    }
}
