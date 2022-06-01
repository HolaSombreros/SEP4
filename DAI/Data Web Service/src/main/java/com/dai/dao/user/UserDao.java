package com.dai.dao.user;

import com.dai.model.User;

import java.util.List;
import java.util.concurrent.Future;

public interface UserDao {
    Future<User> create(User user);
    Future<User> read(int id);
    Future<User> update(User employee);
    Future<User> delete(int id);
    Future<List<User>> readAll();
    Future<User> readByMail(String email);
}
