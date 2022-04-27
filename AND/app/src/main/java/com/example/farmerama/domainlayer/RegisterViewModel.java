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
