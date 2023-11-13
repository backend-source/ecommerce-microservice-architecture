package com.hoangtien2k3.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hoangtien2k3.userservice.domain.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Integer> {

}
