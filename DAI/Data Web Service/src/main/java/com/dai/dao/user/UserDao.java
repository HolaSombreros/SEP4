package com.dai.dao.user;

import com.dai.shared.User;
import com.dai.shared.UserRole;

import java.util.List;
import java.util.concurrent.Future;

public interface UserDao {
    Future<User> create(User user);
    Future<User> read(int id);
    User update(User employee);
    void delete(int id);
    List<User> getAll();
    Future<User> getUserByEmailAndPassword(User user);
}
