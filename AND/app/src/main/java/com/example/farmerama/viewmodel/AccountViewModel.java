package com.example.farmerama.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.farmerama.data.model.User;
import com.example.farmerama.data.repository.UserRepository;

public class AccountViewModel extends AndroidViewModel {
    private UserRepository userRepository;

    public AccountViewModel(Application application){
        super(application);
        this.userRepository = UserRepository.getInstance(application);
    }

    public LiveData<User> getUser(){
        return userRepository.getLoggedInUser();
    }

}
