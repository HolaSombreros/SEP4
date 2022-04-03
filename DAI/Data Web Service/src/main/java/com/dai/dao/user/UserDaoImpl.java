package com.dai.dao.user;

import com.dai.model.User;
import com.dai.model.UserRole;
import com.dai.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.Future;

@Repository
@EnableAsync
public class UserDaoImpl implements UserDao {
    private UserRepository repository;

    @Autowired
    public UserDaoImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    @Async
    public Future<User> create(String name, String email, String password, UserRole role) {
        return new AsyncResult<>(repository.save(new User(name, email,password, role)));
    }

    @Override
    @Async
    public Future<User> read(int id) {
        return new AsyncResult<>(repository.getById(id));
    }

    @Override
    public User update(User employee) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    @Async
    public Future<User> getUserByEmailAndPassword(User user) {
        return new AsyncResult<>(repository.getUserByEmailAndPassword(user.getEmail(), user.getPassword()));
    }
}
