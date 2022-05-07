package com.example.farmerama.domainlayer;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.farmerama.datalayer.model.User;
import com.example.farmerama.datalayer.repository.UserRepository;
import com.example.farmerama.util.ValidationLoginRegister;


public class LoginViewModel extends AndroidViewModel {

    private final UserRepository repository;
    private ValidationLoginRegister validation;

    public LoginViewModel(Application application) {
        super(application);
        repository = UserRepository.getInstance(application);
        repository.retrieveAllEmployees();
        validation = new ValidationLoginRegister();
    }

    public LiveData<String> getErrorMessage(){
        return validation.getErrorMessage();
    }

    public boolean validate(String email, String password) {
        return validation.verifyLogin(email, password);
    }
}
