package com.dai.dao.user;

import com.dai.model.User;
import com.dai.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

@Repository
public class UserDaoImpl implements UserDao {
    private UserRepository repository;

    @Autowired
    public UserDaoImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    @Async
    public Future<User> create(User user) {
        return new AsyncResult<>(repository.save(user));
    }

    @Override
    @Async
    public Future<User> read(int id) {
        Optional<User> byId = repository.findById(id);
        return new AsyncResult<>(byId.get());
    }

    @Override
    @Async
    public Future<User> update(User employee) {
        User byId = repository.findById(employee.getUserId()).get();
        byId.setName(employee.getName());
        byId.setEmail(employee.getEmail());
        byId.setPassword(employee.getPassword());
        return new AsyncResult<>(repository.save(byId));
    }

    @Override
    @Async
    public Future<User> delete(int id) {
        User byId = repository.findById(id).get();
        repository.delete(byId);
        return new AsyncResult<>(byId);
    }

    @Override
    @Async
    public Future<List<User>> readAll() {
        return new AsyncResult<>(repository.findAll());
    }

    @Override
    @Async
    public Future<User> readByMail(String email) {
        return new AsyncResult<>(repository.findFirstByEmail(email));
    }

}
