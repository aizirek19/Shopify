package com.ecommerce.Shopify.service;

import com.ecommerce.Shopify.dto.AddressDTO;
import com.ecommerce.Shopify.entities.Address;
import com.ecommerce.Shopify.mappers.AddressMapper;
import com.ecommerce.Shopify.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Object> getAllAddresses() {
        List<Address> addresses = addressRepository.findAll();
        return addresses.stream()
                .map(AddressMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AddressDTO getAddressById(Long addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new IllegalArgumentException("Address not found with id: " + addressId));
        return AddressMapper.toDTO(address);
    }

    @Override
    public void createAddress(AddressDTO addressDTO) {
        Address address = AddressMapper.toEntity(addressDTO);
        addressRepository.save(address);
    }

    @Override
    public void updateAddress(Long addressId, AddressDTO addressDTO) {
        Address existingAddress = addressRepository.findById(addressId)
                .orElseThrow(() -> new IllegalArgumentException("Address not found with id: " + addressId));
        Address updatedAddress = AddressMapper.toEntity(addressDTO);
        updatedAddress.setId(existingAddress.getId());
        addressRepository.save(updatedAddress);
    }

    @Override
    public void deleteAddress(Long addressId) {
        addressRepository.deleteById(addressId);
    }
}

