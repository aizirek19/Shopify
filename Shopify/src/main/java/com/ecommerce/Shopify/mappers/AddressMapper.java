package com.ecommerce.Shopify.mappers;

import com.ecommerce.Shopify.dto.AddressDTO;
import com.ecommerce.Shopify.dto.AddressDTO;
import com.ecommerce.Shopify.entities.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    static Address toEntity(AddressDTO addressDTO) {
        return null;
    }

    @Mapping(target = "userId", source = "user.id")
    AddressDTO addressToAddressDTO(Address address);

    @Mapping(target = "user", source = "")
    Address addressDTOToAddress(AddressDTO addressDTO);

    static <R> R toDTO(Address address) {

        return null;
    }
}
