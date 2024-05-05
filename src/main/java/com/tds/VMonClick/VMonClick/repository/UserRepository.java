package com.tds.VMonClick.VMonClick.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tds.VMonClick.VMonClick.model.User;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
    List<User> findAll();
}
