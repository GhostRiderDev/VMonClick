package com.tds.VMonClick.VMonClick.model;

import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(value = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements UserDetails {
    @PrimaryKey
    private UUID id;

    private String name;

    private String email;

    private boolean isActivated;

    @CassandraType(type = CassandraType.Name.TEXT, userTypeName = "role")
    private Role role;

    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new Collection<GrantedAuthority>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<GrantedAuthority> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(GrantedAuthority grantedAuthority) {
                return true;
            }

            @Override
            public boolean remove(Object o) {
                return true;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return true;
            }

            @Override
            public boolean addAll(Collection<? extends GrantedAuthority> c) {
                return true;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {}
        };
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActivated;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActivated;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActivated;
    }

    @Override
    public boolean isEnabled() {
        return isActivated;
    }

}
