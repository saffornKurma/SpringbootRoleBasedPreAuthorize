package com.security.rolebasedannotations.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserLoginEntityRepository extends JpaRepository<UserLoginEntity, Long> {
    public Optional<UserLoginEntity> findByUsername(String username);
}
