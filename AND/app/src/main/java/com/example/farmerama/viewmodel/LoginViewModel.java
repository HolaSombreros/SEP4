package com.example.farmerama.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.farmerama.data.model.User;
import com.example.farmerama.data.repository.UserRepository;
import com.example.farmerama.data.util.ValidationLoginRegister;


public class LoginViewModel extends AndroidViewModel {

    private final UserRepository repository;
    private ValidationLoginRegister validation;
    private SharedPreferences sharedPreferences;

    public LoginViewModel(Application application) {
        super(application);
        sharedPreferences = application.getSharedPreferences("Login", Context.MODE_PRIVATE);
        repository = UserRepository.getInstance();
        validation = new ValidationLoginRegister();

        loginUser(sharedPreferences.getString("userEmail", "null"), sharedPreferences.getString("userPassword", "null"));
    }

    public LiveData<String> getErrorMessage(){
        return validation.getErrorMessage();
    }

    public boolean validate(String email, String password) {
        return validation.verifyLogin(email, password);
    }

    public void loginUser(String email, String password) {
        repository.loginUser(new User(email, password));
    }
}
