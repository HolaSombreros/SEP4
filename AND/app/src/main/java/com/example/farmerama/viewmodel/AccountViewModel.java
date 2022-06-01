package com.example.farmerama.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.farmerama.data.model.User;

public class AccountViewModel extends FactoryViewModel {

    public AccountViewModel(Application application){
        super(application);
    }

    public LiveData<User> getUser(){
        return getUserRepository().getLoggedInUser();
    }
}
