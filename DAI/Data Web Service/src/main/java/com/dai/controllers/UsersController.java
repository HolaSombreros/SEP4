package com.dai.controllers;

import com.dai.exceptions.BadRequestException;
import com.dai.exceptions.UnauthorizedException;
import com.dai.model.users.UserModel;
import com.dai.shared.LoginUser;
import com.dai.shared.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/users")
@RestController
public class UsersController {

    private UserModel userModel;

    @Autowired
    public UsersController(UserModel userModel) {
        this.userModel = userModel;
    }

    @PostMapping
    public User create(@Valid @RequestBody User user) {
        try {
            return userModel.create(user);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @GetMapping(value = "/{id}")
    public User read(@PathVariable int id) {
        try {
            return userModel.read(id);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @PutMapping(value = "/{id}")
    public User update(@PathVariable(name = "id") int id,@RequestBody User user) {
        try {
            user.setId(id);
            return userModel.update(user);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public User delete(@PathVariable int id) {
        try {
            return userModel.delete(id);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @GetMapping
    public List<User> getAll() {
        try {
            return userModel.getAll();
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @PostMapping(value = "login")
    public User login(@Valid @RequestBody LoginUser user) {
        try {
            return userModel.login(user);
        } catch (Exception e) {
            throw new UnauthorizedException(e.getMessage());
        }
    }
}
