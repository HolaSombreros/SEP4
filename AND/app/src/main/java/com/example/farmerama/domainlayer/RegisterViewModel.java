package com.example.farmerama.domainlayer;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.farmerama.datalayer.model.User;
import com.example.farmerama.datalayer.repository.UserRepository;

import java.util.List;

public class RegisterViewModel extends AndroidViewModel
{
    private final UserRepository repository;

    public RegisterViewModel(Application application) {
        super(application);
        repository = UserRepository.getInstance();
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

        try{
            repository.getUserByEmail(employee.getEmail(), employee.getPassword());
            User user = repository.getEmployee().getValue();
            int size = employee.getPassword().length();

            // todo check the space in email and password
            if(user == null && size>=6 && user.getEmail().contains("@") && user.getEmail().contains("."))
            {
                repository.register(employee);
            }
        }
        catch (Exception e){

        }
    }
}
