package com.tds.VMonClick.VMonClick.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.tds.VMonClick.VMonClick.dto.UserDto;
import com.tds.VMonClick.VMonClick.mapper.UserMapper;
import com.tds.VMonClick.VMonClick.model.Role;
import com.tds.VMonClick.VMonClick.model.UserEntity;
import com.tds.VMonClick.VMonClick.repository.UserRepository;
import com.tds.VMonClick.VMonClick.security.CustomerDetailsService;
import com.tds.VMonClick.VMonClick.security.jwt.JwtFilter;
import com.tds.VMonClick.VMonClick.security.jwt.JwtUtil;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomerDetailsService customerDetailsService;

    @Autowired
    private JwtUtil jwtUtil;


    @Autowired
    private UserRepository userRepository;

    public List<UserDto> getAllUsers() {
        var users = userRepository.findAll();
        return users.stream().map(UserMapper::DBtoDto).toList();
    }

    public String createUser(UserDto user) {
        var userDB = userRepository.findOneByEmail(user.getEmail());
        if (userDB.isPresent()) {
            return "User already exists";
        }
        UserEntity userEntity = UserMapper.dtoToDB(user);
        userEntity.setId(UUID.randomUUID());
        userEntity.setActivated(false);
        userEntity.setRole(Role.USER);
        userEntity = userRepository.save(userEntity);
        user = UserMapper.DBtoDto(userEntity);
        return "User created";
    }

    public UserDto getUser(String id) {
        var userDB = userRepository.findById(UUID.fromString(id));
        if (!userDB.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found user");
        }
        return UserMapper.DBtoDto(userDB.get());
    }

    public ResponseEntity login(UserDto user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

            if (authentication.isAuthenticated()) {

                return new ResponseEntity<String>("{\"token\":\""
                        + jwtUtil.generateToken(customerDetailsService.getUserDetail().getEmail(),
                                customerDetailsService.getUserDetail().getRole().toString())
                        + "\"}", HttpStatus.OK);
            }
        } catch (Exception exception) {
            log.error("{}", exception);
        }
        return new ResponseEntity<String>("{\"mensaje\":\"" + " Credenciales incorrectas " + "\"}",
                HttpStatus.BAD_REQUEST);
    }

    public UserDto updateUser(String id, UserDto userDto) {
        var userDB = userRepository.findById(UUID.fromString(id));
        if (!userDB.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found user");
        }
        var foundUser = userDB.get();
        foundUser.setEmail(userDto.getEmail());
        foundUser.setName(userDto.getName());
        foundUser.setPassword(userDto.getPassword());

        var userUpdated = userRepository.save(foundUser);
        return UserMapper.DBtoDto(userUpdated);
    }

    public void deleteUser(String id) {
        var userDB = userRepository.findById(UUID.fromString(id));
        if (!userDB.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found user");
        }
        userRepository.deleteById(UUID.fromString(id));
    }

}
