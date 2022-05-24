package com.example.farmerama.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.farmerama.data.model.User;
import com.example.farmerama.data.repository.UserRepository;
import com.example.farmerama.data.util.ValidationUser;

import java.util.List;

public class RegisterViewModel extends AndroidViewModel
{
    private final UserRepository repository;
    private ValidationUser validation;

    public RegisterViewModel(Application application) {
        super(application);
        repository = UserRepository.getInstance();
        validation = new ValidationUser();
    }

    public LiveData<List<User>> getAllEmployees(){
        return repository.getAllEmployees();
    }

    public void retrieveAllEmployees(){
        repository.retrieveAllEmployees();
    }

    public void getUserById(int id) {
        repository.retrieveUserById(id);
    }

    public LiveData<User> getEmployee(){
        return repository.getEmployee();
    }

    public void registerUser(User employee) {
        repository.register(employee);
    }

    public boolean validate(String firstName, String lastName,String email, String password, String role){
        return validation.verifyUserInput(firstName, lastName, email, password, role);
    }
}
