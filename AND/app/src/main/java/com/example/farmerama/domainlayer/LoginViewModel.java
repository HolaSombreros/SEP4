package com.example.farmerama.domainlayer;

import androidx.lifecycle.ViewModel;

import com.example.farmerama.datalayer.model.User;
import com.example.farmerama.datalayer.repository.UserRepository;
import com.example.farmerama.util.ValidationLoginRegister;


public class LoginViewModel extends ViewModel {

    private final UserRepository repository;
    private ValidationLoginRegister validation;

    public LoginViewModel() {
        repository = UserRepository.getInstance();
        repository.retrieveAllEmployees();
        validation = new ValidationLoginRegister();
    }

    public LiveData<String> getErrorMessage(){
        return validation.getErrorMessage();
    }

    public boolean validate(String email, String password) {
        return validation.verifyLogin(email, password);


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
