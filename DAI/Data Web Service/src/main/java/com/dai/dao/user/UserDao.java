package com.dai.dao.user;

import com.dai.model.User;
import com.dai.model.UserRole;

import java.util.List;
import java.util.concurrent.Future;

public interface UserDao {
    Future<User> create(String name, String email, String password, UserRole role);
    Future<User> read(int id);
    User update(User employee);
    void delete(int id);
    List<User> getAll();
    Future<User> getUserByEmailAndPassword(User user);
}
