package com.dai.controllers;

import com.dai.exceptions.BadRequestException;
import com.dai.exceptions.UnauthorizedException;
import com.dai.model.users.UserModel;
import com.dai.shared.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.Future;

//TODO await
@RequestMapping("/users")
@RestController
public class UsersController {

    private UserModel userModel;

    @Autowired
    public UsersController(UserModel userModel) {
        this.userModel = userModel;
    }

    @PostMapping
    public Future<User> create(@RequestBody User user) {
        try {
            return userModel.create(user);
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }


    @GetMapping(value = "/{id}")
    public Future<User> read(@PathVariable int id) {
        try {
            return userModel.read(id);
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }


    @PutMapping(value = "/{id}")
    public User update(@PathVariable(name = "id") int id,@RequestBody User user) {
        try {
            user.setId(id);
            return userModel.update(user);
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }


    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable int id) {
        try {
            userModel.delete(id);
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }

    @GetMapping
    public List<User> getAll() {
        try {
            return userModel.getAll();
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }

    @PostMapping(value = "/login")
    public void login(@RequestBody User user) {
        try {
            userModel.login(user);
        } catch (Exception e) {
            throw new UnauthorizedException();
        }
    }
}
