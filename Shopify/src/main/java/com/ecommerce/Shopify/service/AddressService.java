package com.ecommerce.Shopify.service;

import com.ecommerce.Shopify.dto.AddressDTO;

import java.util.List;

public interface AddressService {
    AddressDTO getAddressById(Long addressId);
    List<Object> getAllAddresses();
    void createAddress(AddressDTO addressDTO);
    void updateAddress(Long addressId, AddressDTO addressDTO);
    void deleteAddress(Long addressId);
}
