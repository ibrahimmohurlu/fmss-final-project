package com.ibrahimmohurlu.user_service.converters;

import com.ibrahimmohurlu.user_service.dto.UserResponseDto;
import com.ibrahimmohurlu.user_service.model.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access=AccessLevel.PRIVATE)
public class UserResponseDtoConverter {
    public static UserResponseDto userToUserResponseDto(User user){
        return UserResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
