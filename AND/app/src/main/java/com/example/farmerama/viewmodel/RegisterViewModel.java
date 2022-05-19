package com.example.farmerama.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.farmerama.data.model.User;
import com.example.farmerama.data.repository.UserRepository;
import com.example.farmerama.data.util.ValidationLoginRegister;

import java.util.List;

public class RegisterViewModel extends AndroidViewModel
{
    private final UserRepository repository;
    private ValidationLoginRegister validation;

    public RegisterViewModel(Application application) {
        super(application);
        repository = UserRepository.getInstance();
        validation = new ValidationLoginRegister();
    }

    public LiveData<List<User>> getAllEmployees(){
        return repository.getAllEmployees();
    }

    public void retrieveAllEmployees(){
        repository.retrieveAllEmployees();
    }

    public void getUserById(int id) {
        repository.getUserById(id);
    }

    public LiveData<User> getEmployee(){
        return repository.getEmployee();
    }

    public void registerUser(User employee) {
        repository.register(employee);
    }

    public boolean validate(String firstName, String lastName,String email, String password, String role){
        return validation.verifyRegister(firstName, lastName, email, password, role);
    }

    public LiveData<String> getErrorMessage(){
        return validation.getErrorMessage();
    }
}
