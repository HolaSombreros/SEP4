package com.example.farmerama.domainlayer;

import androidx.lifecycle.ViewModel;

import com.example.farmerama.datalayer.model.User;
import com.example.farmerama.datalayer.repository.UserRepository;

import java.util.List;

public class LoginViewModel extends ViewModel {

    private final UserRepository repository;
    List<User> users;

    public LoginViewModel() {
        repository = UserRepository.getInstance();
        repository.retrieveAllEmployees();
        users =repository.getAllEmployees().getValue();
    }

    public User login(String email, String password){
        try{

            for (User user:users) {
                if(user.getPassword().equals(password)&& user.getEmail().equals(email))
                {
                    return user;
                }
            }
        }
        catch (Exception e)
        {
        }
        return null;
    }

}
