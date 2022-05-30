package com.dai.service.users;

import com.dai.exceptions.BadRequestException;
import com.dai.helpers.Helper;
import com.dai.dao.user.UserDao;
import com.dai.model.LoginUser;
import com.dai.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Future;

@Component
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User create(User user) throws Exception {
        if (user.getRole() == null)
            throw new Exception("Please assign a role first");
        User userExistingCheck = Helper.await(userDao.readByMail(user.getEmail()));
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
        if (read(user.getUserId()) == null)
            throw new Exception("User with the given ID doesn't exist");
        if(user.getEmail().isEmpty() || user.getUserName().isEmpty() || user.getPassword().isEmpty())
            throw new Exception("Please fill in all the fields");
        return Helper.await(userDao.update(user));
    }

    @Override
    public User delete(int id) throws Exception {
        if (read(id) == null)
            throw new Exception("User with the given ID doesn't exist");
        return Helper.await(userDao.delete(id));
    }

    @Override
    public List<User> readAll() throws Exception {
        return Helper.await(userDao.readAll());
    }

    @Override
    public User login(LoginUser user) throws Exception {
        Future<User> userFuture = userDao.readByMail(user.getEmail());
        User dbUser = Helper.await(userFuture);
        if (dbUser == null) {
            throw new Exception("Email not registered");
        } else if (!dbUser.getPassword().equals(user.getPassword())) {
            throw new Exception("Invalid email/password combination");
        }
        return dbUser;
    }
}
