package com.example.farmerama.domainlayer;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.farmerama.datalayer.model.User;
import com.example.farmerama.datalayer.repository.UserRepository;

import java.util.List;

public class LoginViewModel extends AndroidViewModel {

    private final UserRepository repository;
    private List<User> users;

    public LoginViewModel(Application application) {
        super(application);
        repository = UserRepository.getInstance(application);
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
