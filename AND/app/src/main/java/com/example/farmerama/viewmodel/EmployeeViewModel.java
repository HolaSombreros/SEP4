package com.example.farmerama.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.farmerama.data.model.User;
import com.example.farmerama.data.util.ToastMessage;

import java.util.List;

public class EmployeeViewModel extends FactoryViewModel {

    public EmployeeViewModel(@NonNull Application application) {
        super(application);
    }

    public void getUserById(int id) {
        getUserRepository().retrieveUserById(id);
    }

    public LiveData<List<User>> getAllEmployees(){
        return getUserRepository().getAllEmployees();
    }


    public void retrieveAllEmployees(){
        getUserRepository().retrieveAllEmployees();
    }

    public LiveData<User> getEmployee(){
        return getUserRepository().getEmployee();
    }

    public LiveData<User> getLoggedInUser() {
        return getUserRepository().getLoggedInUser();
    }


    public void deleteEmployeeById(User user) {
        if(!(getLoggedInUser().getValue().getUserId() == user.getUserId())) {
            getUserRepository().deleteEmployeeById(user.getUserId());
        }
        else {
            ToastMessage.setToastMessage("Cannot delete yourself");
        }
    }


}
