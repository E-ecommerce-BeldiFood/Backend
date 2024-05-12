package ma.beldifood.User.mappers;

import ma.beldifood.User.entities.User;
import ma.beldifood.User.dto.UserRequestDto;
import ma.beldifood.User.dto.UserResponseDto;
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
    public static User mapFromResponseDtoToUserEntity(UserResponseDto userResponseDto) {
        return modelMapper.map(userResponseDto, User.class);
    }
}
