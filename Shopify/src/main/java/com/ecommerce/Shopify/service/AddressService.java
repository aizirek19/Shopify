package com.ecommerce.Shopify.service;

import com.ecommerce.Shopify.dto.AddressDTO;

import java.util.List;

public interface AddressService {
    AddressDTO getAddressById(Long addressId);
    List<Object> getAllAddresses();
    AddressDTO createAddress(AddressDTO addressDTO);
    AddressDTO updateAddress(Long addressId, AddressDTO addressDTO);
    void deleteAddress(Long addressId);
}
