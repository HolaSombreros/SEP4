package com.example.farmerama.domainlayer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.farmerama.datalayer.model.User;
import com.example.farmerama.datalayer.repository.UserRepository;
import com.example.farmerama.util.ValidationLoginRegister;

import java.util.ArrayList;
import java.util.List;

public class LoginViewModel extends ViewModel {

    private final UserRepository repository;
    private ValidationLoginRegister validation;

    public LoginViewModel() {
        repository = UserRepository.getInstance();
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
