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

        try{
            repository.getUserByEmail(employee.getEmail());
            User user = repository.getEmployee().getValue();
            int size = employee.getPassword().length();
            if(user == null && size>=6 )
            {
                repository.register(employee);
            }
        }
        catch (Exception e){

        }
    }
}
