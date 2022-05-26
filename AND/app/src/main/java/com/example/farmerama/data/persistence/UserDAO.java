package com.example.farmerama.data.persistence;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.farmerama.data.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO {
    private MutableLiveData<List<User>> users;
    private static UserDAO userDAO;
    private static Object lock = new Object();

    public static UserDAO getInstance() {
        if(userDAO == null) {
            synchronized (lock) {
                if(userDAO == null) {
                    userDAO = new UserDAO();
                }
            }
        }
        return userDAO;
    }

    private UserDAO() {
        users = new MutableLiveData<>();
        users.setValue(new ArrayList<>());
    }
    @Override
    public void registerUser(User user) {
        List<User> currentUsers = users.getValue();
        currentUsers.add(user);
        users.postValue(currentUsers);
    }

    @Override
    public void removeUser(User user) {

    }

    @Override
    public void removeUsers() {
        users.postValue(new ArrayList<>());
    }

    @Override
    public LiveData<List<User>> getAllEmployees() {
        return users;
    }

    @Override
    public LiveData<User> getLoggedUser(String email, String password) {
        return null;
    }

    @Override
    public LiveData<User> getEmployeeById(int id) {
        return null;
    }
}
