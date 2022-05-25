package com.dai.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginUser {

    @NotNull(message = "Please fill in all the required fields")
    @Email(message = "Email is not valid")
    private String email;

    @NotNull(message = "Please fill in all the required fields")
    @Size(min = 6, message = "Password is not valid")
    private String password;

    public LoginUser(String email, String password) {
        this.email = email;
        this.password = password;
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
}
