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
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping()
    public ResponseEntity<List<UserDto>> getAllUsers() {
        try {
            return ResponseEntity.ok().body(userService.getAllUsers());
        } catch (Exception e) {
            System.out.println("HAY UN ERROR****************+");
            throw e;
            // throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
            // "Unavailable server");
        }
    }

    @PostMapping()
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user) {
        try {
            return ResponseEntity.ok().body(userService.createUser(user));
        } catch (Exception e) {
            System.out.println("HAY UN ERROR****************+");
            throw e;
            // throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
            // "Unavailable server");
        }
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
    public ResponseEntity updateUser(@Valid @PathVariable("id") String uuid) {
        try {
            userService.deleteUser(uuid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Unavailable server");
        }
    }



}
