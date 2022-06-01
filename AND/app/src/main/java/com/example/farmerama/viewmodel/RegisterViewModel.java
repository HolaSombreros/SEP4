package com.example.farmerama.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.farmerama.data.model.User;
import com.example.farmerama.data.util.ValidationUser;

public class RegisterViewModel extends FactoryViewModel
{
    private ValidationUser validation;

    public RegisterViewModel(Application application) {
        super(application);
        validation = new ValidationUser();
    }

    public void registerUser(User employee) {
        getUserRepository().register(employee);
    }

    public LiveData<User> getLoggedInUser() {
        return getUserRepository().getLoggedInUser();
    }

    public boolean validate(String firstName, String lastName,String email, String password, String role){
        return validation.verifyUserInput(firstName, lastName, email, password, role);
    }

}
