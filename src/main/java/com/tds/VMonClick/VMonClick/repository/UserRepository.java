package com.tds.VMonClick.VMonClick.repository;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tds.VMonClick.VMonClick.model.UserEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, UUID> {
    List<UserEntity> findAll();

    @Query(value = "SELECT * FROM user WHERE email=?0 ALLOW FILTERING")
    Optional<UserEntity> findOneByEmail(String email);
}
