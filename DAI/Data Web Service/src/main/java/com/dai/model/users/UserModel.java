package com.dai.model.users;


import com.dai.shared.User;
import com.dai.shared.UserRole;

import java.util.List;
import java.util.concurrent.Future;

public interface UserModel {
    Future<User> create(User user);
    Future<User> read(int id);
    User update(User employee);
    void delete(int id);
    List<User> getAll();
    void login(User user);
}
