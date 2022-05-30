package com.example.farmerama.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.farmerama.data.model.User;
import com.example.farmerama.data.repository.UserRepository;

public class AccountViewModel extends FactoryViewModel {

    public AccountViewModel(Application application){
        super(application);
    }

    public LiveData<User> getUser(){
        return getUserRepository().getLoggedInUser();
    }

    public void deleteEmployeeById(int userId) {
        getUserRepository().deleteEmployeeById(userId);
    }
}
