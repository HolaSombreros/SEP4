package com.example.farmerama.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.farmerama.data.model.User;
import com.example.farmerama.data.repository.UserRepository;
import com.example.farmerama.data.util.ValidationUser;

public class EditAccountViewModel extends FactoryViewModel {

    private ValidationUser validation;
    private int userId;

    public EditAccountViewModel(@NonNull Application application) {
        super(application);
        validation = new ValidationUser();
    }

    public LiveData<User> getLoggedInUser() {
        return getUserRepository().getLoggedInUser();
    }

    public void saveAccount(User user) {
        getUserRepository().updateUser(user);
    }

    public boolean validate(String firstName, String lastName,String email, String password, String role){
        return validation.verifyUserInput(firstName, lastName, email, password, role);
    }

    public void setUserId(int id) {
        userId = id;
    }

    public int getUserId() {
        return userId;
    }
}
