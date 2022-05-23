package com.example.farmerama.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.farmerama.data.model.User;
import com.example.farmerama.data.repository.UserRepository;

public class AccountViewModel extends ViewModel {
    private UserRepository userRepository;

    public AccountViewModel(){
        this.userRepository = UserRepository.getInstance();
    }

    public LiveData<User> getUser(){
        return userRepository.getLoggedInUser();
    }

}
