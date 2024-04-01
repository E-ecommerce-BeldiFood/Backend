package com.example.User.service;

import com.example.User.entities.User;
import com.example.User.entities.dto.UserRequestDto;
import com.example.User.entities.dto.UserResponseDto;
import com.example.User.exception.EmailAlreadyExistsException;
import com.example.User.exception.EmptyEntityException;
import com.example.User.repository.UserRepository;
import com.example.User.utils.Mapping;
import com.example.User.utils.UserInputValidation;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{
    private  final UserRepository userRepository;
    private final UserInputValidation userInputValidation;



    @Override
    public List<UserResponseDto> getAllUsers() throws EntityNotFoundException{
        log.info("Fetching all users");
        if(userRepository.findAll().isEmpty()){
            throw new EntityNotFoundException("No user found!");
        }else {
            return userRepository.findAll()
                    .stream()
                    .map(Mapping::mapToUserResponseDto)
                    .toList();
        }

    }

    @Override
    public UserResponseDto getUserById(Long id) throws EntityNotFoundException, EmptyEntityException {
        log.info("Fetching user by id: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        return Mapping.mapToUserResponseDto(user);
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userDto) throws EmailAlreadyExistsException {
        // Validate email and password
        if (!UserInputValidation.isValidEmail(userDto.getEmail())) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (!userInputValidation.isValidPassword(userDto.getPassword())) {
            throw new IllegalArgumentException("Invalid password format");
        }
        // Check if email already exists
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists: " + userDto.getEmail());
        }

        User user = Mapping.mapToUserEntity(userDto);
        return Mapping.mapToUserResponseDto(userRepository.save(user));
    }

    @Override
    public UserResponseDto getUserByUserName(String userName) throws EntityNotFoundException {
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new EntityNotFoundException("User not found with username: " + userName));
        return Mapping.mapToUserResponseDto(user);
    }


    @Override
    public void deleteUserById(Long id) throws EntityNotFoundException, EmptyEntityException{
        userRepository.deleteById(id);

    }

    @Override
    public UserResponseDto updateUser(UserRequestDto userDto) throws EntityNotFoundException {
        User user = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userDto.getId()));


        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());

        User updatedUser = userRepository.save(user);
        return Mapping.mapToUserResponseDto(updatedUser);

    }


}
