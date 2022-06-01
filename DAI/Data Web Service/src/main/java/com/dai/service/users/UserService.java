package com.dai.service.users;


import com.dai.model.LoginUser;
import com.dai.model.User;

import java.util.List;

public interface UserService {
    User create(User user) throws Exception;
    User read(int id) throws Exception;
    User update(User employee) throws Exception;
    User delete(int id) throws Exception;
    List<User> readAll() throws Exception;
    User login(LoginUser user) throws Exception;
}
