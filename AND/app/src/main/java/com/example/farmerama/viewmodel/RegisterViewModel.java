package com.example.farmerama.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.farmerama.data.model.User;
import com.example.farmerama.data.repository.UserRepository;
import com.example.farmerama.data.util.ValidationUser;

import java.util.List;

public class RegisterViewModel extends FactoryViewModel
{
    private ValidationUser validation;

    public RegisterViewModel(Application application) {
        super(application);
        validation = new ValidationUser();
    }

    public LiveData<List<User>> getAllEmployees(){
        return getUserRepository().getAllEmployees();
    }

    public void retrieveAllEmployees(){
        getUserRepository().retrieveAllEmployees();
    }

    public void getUserById(int id) {
        getUserRepository().retrieveUserById(id);
    }

    public LiveData<User> getEmployee(){
        return getUserRepository().getEmployee();
    }

    public void registerUser(User employee) {
        getUserRepository().register(employee);
    }

    public LiveData<User> getLoggedInUser() {
        return repository.getLoggedInUser();
    }

    public boolean validate(String firstName, String lastName,String email, String password, String role){
        return validation.verifyUserInput(firstName, lastName, email, password, role);
    }

    public void deleteEmployeeById(User id)
    {
        repository.deleteEmployeeById(id.getId());
    }
}
