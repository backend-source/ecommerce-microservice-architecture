package com.hoangtien2k3.userservice.service;

import java.util.List;

import com.hoangtien2k3.userservice.dto.AddressDto;

public interface AddressService {
    List<AddressDto> findAll();
    AddressDto findById(final Integer addressId);
    AddressDto save(final AddressDto addressDto);
    AddressDto update(final AddressDto addressDto);
    AddressDto update(final Integer addressId, final AddressDto addressDto);
    void deleteById(final Integer addressId);
}