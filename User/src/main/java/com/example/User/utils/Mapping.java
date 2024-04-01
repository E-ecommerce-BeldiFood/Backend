package com.example.User.utils;

import com.example.User.entities.User;
import com.example.User.entities.dto.UserRequestDto;
import com.example.User.entities.dto.UserResponseDto;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor
public class Mapping {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static User mapToUserEntity(UserRequestDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    public static UserResponseDto mapToUserResponseDto(User user) {
        UserResponseDto userResponseDto = modelMapper.map(user, UserResponseDto.class);
        return userResponseDto;
    }
}
