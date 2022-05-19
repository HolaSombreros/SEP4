package com.example.farmerama.domainlayer;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.farmerama.datalayer.model.User;
import com.example.farmerama.datalayer.repository.SuccessResponse;
import com.example.farmerama.datalayer.repository.UserRepository;
import com.example.farmerama.util.ValidationLoginRegister;


public class LoginViewModel extends AndroidViewModel {

    private final UserRepository repository;
    private ValidationLoginRegister validation;

    public LoginViewModel(Application application) {
        super(application);
        repository = UserRepository.getInstance();
        validation = new ValidationLoginRegister();
    }

    public LiveData<String> getErrorMessage(){
        return validation.getErrorMessage();
    }

    public LiveData<String> getErrorMessageRepo() {
        return repository.getError();
    }

    public boolean validate(String email, String password) {
        return validation.verifyLogin(email, password);
    }

    public SuccessResponse getSuccessResponse() {
        return repository.getSuccessResponse();
    }

    public void loginUser(String email, String password) {
        repository.loginUser(new User(email, password));
    }
}
