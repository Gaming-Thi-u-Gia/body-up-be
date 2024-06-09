package com.bodyupbe.bodyupbe.dto.mapper.user;
import com.bodyupbe.bodyupbe.dto.request.user.UserRequestDto;
import com.bodyupbe.bodyupbe.dto.response.user.UserResponseDto;
import com.bodyupbe.bodyupbe.model.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserRequestDto userRequestDto);
    UserResponseDto toUserResponseDto(User user);
}
