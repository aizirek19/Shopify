package com.ecommerce.Shopify.mappers;
import com.ecommerce.Shopify.dto.UserDTO;
import com.ecommerce.Shopify.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "password", ignore = true) // Игнорируем поле password при преобразовании в DTO
    UserDTO userToUserDTO(User user);

    User userDTOToUser(UserDTO userDTO);
}

