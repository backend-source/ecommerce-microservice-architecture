package com.hoangtien2k3.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hoangtien2k3.userservice.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
