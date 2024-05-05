package com.tds.VMonClick.VMonClick.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tds.VMonClick.VMonClick.model.Role;
import com.tds.VMonClick.VMonClick.model.User;
import com.tds.VMonClick.VMonClick.repository.UserRepository;

@Service
public class UserService {
    @Autowired(required = true)
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        user.setId(UUID.randomUUID());
        user.setActivated(false);
        user.setRole(Role.USER);
        return userRepository.save(user);
    }
}
