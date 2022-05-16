package com.example.farmerama.domainlayer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.farmerama.datalayer.repository.ErrorRepository;
import com.example.farmerama.datalayer.repository.UserRepository;

public class MainActivityViewModel extends ViewModel {

    private UserRepository userRepository;



    public MainActivityViewModel() {
        super();
        userRepository = UserRepository.getInstance();
    }

    public LiveData<Boolean> getLoggedUser() {
        return userRepository.isLoggedIn();
    }





}
