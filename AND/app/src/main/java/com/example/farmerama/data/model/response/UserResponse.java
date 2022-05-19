package com.example.farmerama.data.model.response;

import com.example.farmerama.data.model.User;

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
