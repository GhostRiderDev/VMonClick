package com.tds.VMonClick.VMonClick.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.tds.VMonClick.VMonClick.dto.UserDto;
import com.tds.VMonClick.VMonClick.service.UserService;
import com.typesafe.config.ConfigException.Null;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<List<UserDto>> getAllUsers() {
        try {
            return ResponseEntity.ok().body(userService.getAllUsers());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Unavailable server");
        }
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserDto user) {
        try {
            return userService.login(user);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Unavailable server");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserDto user) {
        var response = userService.createUser(user);
        if (response.equals("User already exists")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@Valid @PathVariable("id") String uuid) {
        try {
            return ResponseEntity.ok().body(userService.getUser(uuid));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Unavailable server");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@Valid @PathVariable("id") String uuid,
            @Valid UserDto userDto) {
        try {
            return ResponseEntity.ok().body(userService.updateUser(uuid, userDto));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Unavailable server");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Null> updateUser(@Valid @PathVariable("id") String uuid) {
        try {
            userService.deleteUser(uuid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Unavailable server");
        }
    }

}
