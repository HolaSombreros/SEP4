package com.example.farmerama.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.farmerama.data.model.User;
import com.example.farmerama.data.util.ValidationUser;


public class LoginViewModel extends FactoryViewModel {

    private ValidationUser validation;
    private SharedPreferences loginSharedPreferences;
    private SharedPreferences introSharedPreferences;

    public LoginViewModel(Application application) {
        super(application);
        loginSharedPreferences = application.getSharedPreferences("Login", Context.MODE_PRIVATE);
        introSharedPreferences = application.getSharedPreferences("Intro", Context.MODE_PRIVATE);

        validation = new ValidationUser();

        if (!loginSharedPreferences.getString("userEmail", "null").equals("null"))
            loginUser(loginSharedPreferences.getString("userEmail", "null"), loginSharedPreferences.getString("userPassword", "null"));
    }

    public boolean validate(String email, String password) {
        return validation.verifyLogin(email, password);
    }

    public void loginUser(String email, String password) {
        getUserRepository().loginUser(new User(email, password));
    }

    public boolean isSeen() {
        return introSharedPreferences.getBoolean("Seen", false);
    }

    public void setSeen(boolean bool) {
        introSharedPreferences.edit().putBoolean("Seen", bool).apply();
    }
}
