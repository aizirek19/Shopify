package com.ecommerce.Shopify.mappers;

import com.ecommerce.Shopify.dto.AddressDTO;
import com.ecommerce.Shopify.entities.Address;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressMapperTest {

    @Test
    public void testAddressToAddressDTO() {
        // Создаем объект Address
        Address address = new Address();
        address.setId(1L);
        address.setStreet("123 Main St");
        address.setCity("New York");
        address.setState("NY");
        address.setZipCode("10001");

        // Преобразуем его в DTO
        AddressDTO addressDTO = AddressMapper.INSTANCE.addressToAddressDTO(address);

        // Проверяем, что поля были правильно скопированы
        assertEquals(address.getId(), addressDTO.getId());
        assertEquals(address.getStreet(), addressDTO.getStreet());
        assertEquals(address.getCity(), addressDTO.getCity());
        assertEquals(address.getState(), addressDTO.getState());
        assertEquals(address.getZipCode(), addressDTO.getZipCode());
    }

    @Test
    public void testAddressDTOToAddress() {
        // Создаем объект AddressDTO
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(1L);
        addressDTO.setStreet("123 Main St");
        addressDTO.setCity("New York");
        addressDTO.setState("NY");
        addressDTO.setZipCode("10001");

        // Преобразуем его в сущность Address
        Address address = AddressMapper.INSTANCE.addressDTOToAddress(addressDTO);

        // Проверяем, что поля были правильно скопированы
        assertEquals(addressDTO.getId(), address.getId());
        assertEquals(addressDTO.getStreet(), address.getStreet());
        assertEquals(addressDTO.getCity(), address.getCity());
        assertEquals(addressDTO.getState(), address.getState());
        assertEquals(addressDTO.getZipCode(), address.getZipCode());
    }
}
