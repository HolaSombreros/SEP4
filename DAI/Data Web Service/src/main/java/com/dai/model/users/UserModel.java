package com.dai.model.users;


import com.dai.shared.LoginUser;
import com.dai.shared.User;

import java.util.List;

public interface UserModel {
    User create(User user) throws Exception;
    User read(int id) throws Exception;
    User update(User employee) throws Exception;
    User delete(int id) throws Exception;
    List<User> getAll() throws Exception;
    User login(LoginUser user) throws Exception;
}
