package com.security.rolebasedannotations.security;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name="user_login")
public class UserLoginEntity implements UserDetails {

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(nullable = false, unique = true)
    String username;

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(nullable = false)
    String password;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    String role;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public List<UserPermissionEntity> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<UserPermissionEntity> permissions) {
        this.permissions = permissions;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<UserPermissionEntity> permissions=new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();

        // ✅ Prefix role with "ROLE_" to support hasRole("USER")
        if (role != null) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
            System.out.println("Added ROLE_" + role);
        }

        if (permissions != null) {
            for (UserPermissionEntity permission : permissions) {
                if (permission.getName() != null) {
                    authorities.add(new SimpleGrantedAuthority(permission.getName()));
                    System.out.println("Added Permission: " + permission.getName());
                }
            }
        }
        else
        {
            System.out.println("No permissions added");
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
