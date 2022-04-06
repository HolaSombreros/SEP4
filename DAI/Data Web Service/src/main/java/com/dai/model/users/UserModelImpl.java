package com.dai.model.users;

import com.dai.dao.user.UserDao;
import com.dai.shared.User;
import com.dai.shared.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

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
    public Future<User> create(User user) {
        return userDao.create(user);
    }

    @Override
    public Future<User> read(int id) {
        return userDao.read(id);
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public void delete(int id) {
        userDao.delete(id);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public void login(User user) {
        //TODO implement and throw exception if not in the system
    }
}
