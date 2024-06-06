package com.bodyupbe.bodyupbe.dto.mapper.recipe;

import com.bodyupbe.bodyupbe.dto.request.AuthenticationResponseDto;
import com.bodyupbe.bodyupbe.service.AuthenticationResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthenticationResponseMapper {
    AuthenticationResponseDto toDto(AuthenticationResponse authenticationResponse);
    AuthenticationResponse toEntity(AuthenticationResponseDto authenticationResponseDto);
}
