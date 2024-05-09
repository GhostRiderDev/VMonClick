package com.tds.VMonClick.VMonClick.model;

import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(value = "User")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @PrimaryKey
    private UUID id;

    private String name;

    private String email;

    private boolean isActivated;

    @CassandraType(type = CassandraType.Name.TEXT, userTypeName = "role")
    private Role role;

    private String password;

}
