package com.example.farmerama.domainlayer;


import androidx.lifecycle.ViewModel;

import com.example.farmerama.datalayer.model.User;
import com.example.farmerama.datalayer.repository.UserRepository;

public class RegisterViewModel extends ViewModel
{
    private final UserRepository repository;


    public RegisterViewModel() {
        repository = UserRepository.getInstance();
    }

    public void registerUser(User employee) {
        repository.register(employee);
    }
}
