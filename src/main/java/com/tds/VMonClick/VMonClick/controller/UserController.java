package com.tds.VMonClick.VMonClick.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tds.VMonClick.VMonClick.model.User;
import com.tds.VMonClick.VMonClick.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping()
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping()
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

}
