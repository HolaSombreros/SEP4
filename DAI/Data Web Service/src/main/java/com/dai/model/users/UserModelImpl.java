package com.dai.model.users;

import com.dai.Helper;
import com.dai.dao.user.UserDao;
import com.dai.shared.LoginUser;
import com.dai.shared.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Future;

@Component
public class UserModelImpl implements UserModel {

    private UserDao userDao;

    @Autowired
    public UserModelImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User create(User user) throws Exception {
//        The email must:
//        contain “@” and “.” characters
//
//        The password must:
//        contain at least 6 characters


        //TODO Validate email address
//        Email already exists
        //TODO Validate password

        //TODO Required fields

        User userExistingCheck = Helper.await(userDao.getUserByMail(user.getEmail()));
        if (userExistingCheck != null) {
            throw new Exception("Email already exists");
        }
        return Helper.await(userDao.create(user));
    }
    @Override
    public User read(int id) throws Exception {
        User await;
        try {
            await = Helper.await(userDao.read(id));
        } catch (Exception e) {
            throw new Exception("User with the given ID doesn't exist");
        }
        return await;
    }
    @Override
    public User update(User user) throws Exception {
        return Helper.await(userDao.update(user));
    }

    @Override
    public User delete(int id) throws Exception {
        return Helper.await(userDao.delete(id));
    }

    @Override
    public List<User> getAll() throws Exception {
        return Helper.await(userDao.getAll());
    }

    @Override
    public User login(LoginUser user) throws Exception {
        Future<User> userFuture = userDao.getUserByMail(user.getEmail());
        User dbUser = Helper.await(userFuture);
        if (dbUser == null)
        {
            throw new Exception("Email not registered");
        } else if(!dbUser.getPassword().equals(user.getPassword())) {
            throw new Exception("Invalid email/password combination");
        }
        return dbUser;
    }
}
