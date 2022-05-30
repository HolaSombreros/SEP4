package com.example.farmerama.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {
    @PrimaryKey
    private int userId;
    private String userName;
    private String email;
    private String password;
    @NonNull
    private UserRole role;

    public User(){}

    public User(String firstname,String lastname, String email, String password,UserRole role) {
        this.userName = firstname + " " + lastname;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(int userId, String userName, String email, String password, UserRole role) {
        this.userId = userId;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String email, String password, UserRole role) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.userName = "GUEST";
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }


}
