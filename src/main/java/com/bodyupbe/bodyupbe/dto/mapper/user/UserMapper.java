package com.bodyupbe.bodyupbe.dto.mapper.user;

import com.bodyupbe.bodyupbe.dto.request.user.UserDto;
import com.bodyupbe.bodyupbe.model.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);

    User toUser(UserDto userDto);
}
