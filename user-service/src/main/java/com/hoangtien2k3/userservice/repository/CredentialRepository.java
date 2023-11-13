package com.hoangtien2k3.userservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hoangtien2k3.userservice.domain.Credential;

public interface CredentialRepository extends JpaRepository<Credential, Integer> {

    Optional<Credential> findByUsername(final String username);

}
