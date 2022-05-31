package com.example.farmerama.data.model.response;

import com.example.farmerama.data.model.User;
import com.example.farmerama.data.model.UserRole;

public class UserResponse {
    private int userId;
    private String userName;
    private String email;
    private String password;
    private UserRole role;

    public User getUser() {
        return new User(userId, userName, email, password, role);
    }
}
