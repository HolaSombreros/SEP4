package com.example.farmerama.datalayer.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.farmerama.datalayer.dao.UserDAO;
import com.example.farmerama.datalayer.model.User;

import java.util.List;

public class UserRepository {

    private UserDAO userDAO;

    public MutableLiveData<List<User>> getAllEmployees() {
        return userDAO.getAllEmployees();
    }

    public User registerUser(User user) {
        return userDAO.register(user);
    }

    public User login(String email, String password) {
        return userDAO.login(email, password);
    }
}
