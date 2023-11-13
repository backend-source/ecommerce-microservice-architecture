package com.hoangtien2k3.userservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hoangtien2k3.userservice.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByCredentialUsername(final String username);

}
