package com.dai.model.users;

import com.dai.Helper;
import com.dai.dao.user.UserDao;
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
    public User login(User user) throws Exception {
        Future<User> userFuture = userDao.getUserByMail(user.getEmail());
        User dbUser = Helper.await(userFuture);
        if (dbUser == null)
        {
            throw new Exception("User with the given email doesn't exist!");
        } else if(!dbUser.getPassword().equals(user.getPassword())) {
            throw new Exception("Password is incorrect");
        }
        return dbUser;
    }
}
