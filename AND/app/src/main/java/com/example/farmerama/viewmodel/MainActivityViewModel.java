package com.example.farmerama.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.farmerama.data.model.User;
import com.example.farmerama.data.repository.UserRepository;

public class MainActivityViewModel extends ViewModel {

    private UserRepository userRepository;



    public MainActivityViewModel() {
        super();
        userRepository = UserRepository.getInstance();
    }

    public LiveData<User> getLoggedInUser() {
        return userRepository.getLoggedInUser();
    }


    public void logOut() {
        userRepository.logOut();
    }
}
