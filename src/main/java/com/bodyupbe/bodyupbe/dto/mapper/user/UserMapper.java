package com.bodyupbe.bodyupbe.dto.mapper.user;

import com.bodyupbe.bodyupbe.dto.request.user.UserDto;
import com.bodyupbe.bodyupbe.model.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface UserMapper {
    User toUser(UserDto userDto);
    UserDto toUserDto(User user);
}
