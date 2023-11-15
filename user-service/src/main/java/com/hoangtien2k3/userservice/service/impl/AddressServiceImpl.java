package com.hoangtien2k3.userservice.service.impl;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.hoangtien2k3.userservice.dto.AddressDto;
import com.hoangtien2k3.userservice.exception.wrapper.AddressNotFoundException;
import com.hoangtien2k3.userservice.helper.AddressMappingHelper;
import com.hoangtien2k3.userservice.repository.AddressRepository;
import com.hoangtien2k3.userservice.service.AddressService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public List<AddressDto> findAll() {
        log.info("AddressDto List, service; fetch all addresss");
        return this.addressRepository.findAll()
                .stream()
                .map(AddressMappingHelper::map)
                .distinct()
                .toList();
    }

    @Override
    public AddressDto findById(final Integer addressId) {
        log.info("AddressDto, service; fetch address by id");
        return this.addressRepository.findById(addressId)
                .map(AddressMappingHelper::map)
                .orElseThrow(() ->
                        new AddressNotFoundException(String.format("Address with id: %d not found", addressId))
                );
    }

    @Override
    public AddressDto save(final AddressDto addressDto) {
        log.info("AddressDto, service; save address");
        return AddressMappingHelper.map(this.addressRepository.save(
                AddressMappingHelper.map(addressDto))
        );
    }

    @Override
    public AddressDto update(final AddressDto addressDto) {
        log.info("AddressDto, service; update address");
        return AddressMappingHelper.map(this.addressRepository.save(
                AddressMappingHelper.map(addressDto))
        );
    }

    @Override
    public AddressDto update(final Integer addressId, final AddressDto addressDto) {
        log.info("AddressDto, service; update address with addressId");
        return AddressMappingHelper.map(this.addressRepository.save(
                AddressMappingHelper.map(this.findById(addressId)))
        );
    }

    @Override
    public void deleteById(final Integer addressId) {
        log.info("Void, service; delete address by id");
        this.addressRepository.deleteById(addressId);
    }

}