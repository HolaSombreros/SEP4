package com.example.farmerama.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.AndroidViewModel;

import com.example.farmerama.data.model.User;
import com.example.farmerama.data.repository.UserRepository;
import com.example.farmerama.data.util.ValidationUser;


public class LoginViewModel extends FactoryViewModel {

    private ValidationUser validation;
    private SharedPreferences sharedPreferences;

    public LoginViewModel(Application application) {
        super(application);
        sharedPreferences = application.getSharedPreferences("Login", Context.MODE_PRIVATE);
        validation = new ValidationUser();

        if (!sharedPreferences.getString("userEmail", "null").equals("null"))
            loginUser(sharedPreferences.getString("userEmail", "null"), sharedPreferences.getString("userPassword", "null"));
    }

    public boolean validate(String email, String password) {
        return validation.verifyLogin(email, password);
    }

    public void loginUser(String email, String password) {
        getUserRepository().loginUser(new User(email, password));
    }
}
