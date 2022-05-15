package com.dai.dao.user;

import com.dai.shared.User;
import com.dai.shared.UserRole;

import java.util.List;
import java.util.concurrent.Future;

public interface UserDao {
    Future<User> create(User user);
    Future<User> read(int id);
    Future<User> update(User employee);
    Future<User> delete(int id);
    Future<List<User>> getAll();
    Future<User> getUserByMail(String email);
}
