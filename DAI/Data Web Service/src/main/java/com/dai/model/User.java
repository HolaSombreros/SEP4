package com.dai.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @NotNull(message = "Please fill in all the required fields")
    @NotEmpty(message = "Please fill in all the required fields")
    @Column(name = "name")
    private String name;

    @NotNull(message = "Please fill in all the required fields")
    @NotEmpty(message = "Please fill in all the required fields")
    @Email(message = "Email is not valid")
    @Column(name = "email")
    private String email;

    @NotNull(message = "Please fill in all the required fields")
    @NotEmpty(message = "Please fill in all the required fields")
    @Size(min = 6, message = "Password is not valid")
    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole role = UserRole.EMPLOYEE;

    public User() {
    }

    public User(String name, String email, String password, UserRole role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public UserRole getRole() {
        return role;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
