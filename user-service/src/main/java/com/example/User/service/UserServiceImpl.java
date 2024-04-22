package com.example.User.service;

import com.example.User.entities.User;
import com.example.User.dto.UserRequestDto;
import com.example.User.dto.UserResponseDto;
import com.example.User.exception.EmailAlreadyExistsException;
import com.example.User.exception.EmptyEntityException;
import com.example.User.repository.UserRepository;
import com.example.User.mappers.Mapping;
import com.example.User.utils.UserInputValidation;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public UserResponseDto getUserById(Long userId) throws EntityNotFoundException, EmptyEntityException {
        log.info("Fetching user by id: {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        return Mapping.mapToUserResponseDto(user);
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userDto) throws EmailAlreadyExistsException, EmptyEntityException {
        log.info("Creating new user with email : {}", userDto.getEmail());

        if (!UserInputValidation.isValidUsername(userDto.getUsername())) {
            throw new EmptyEntityException("Username is required");
        }
        if (!UserInputValidation.isValidFirstName(userDto.getFirstname())) {
            throw new EmptyEntityException("FirstName is required");
        }
        if (!UserInputValidation.isValidLastName(userDto.getLastname())) {
            throw new EmptyEntityException("LastName is required");
        }
        if (!UserInputValidation.isValidEmail(userDto.getEmail())) {
            throw new EmptyEntityException("Invalid email format");
        }
        if (!UserInputValidation.isValidPassword(userDto.getPassword())) {
            throw new EmptyEntityException("Invalid password format");
        }
        // Check if email already exists
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists: " + userDto.getEmail());
        }

        User user = Mapping.mapToUserEntity(userDto);
        user.setActive(true);
        return Mapping.mapToUserResponseDto(userRepository.save(user));
    }

    @Override
    public UserResponseDto getUserByUserName(String userName) throws EntityNotFoundException {
        log.info("Fetching user by username: {}", userName);
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new EntityNotFoundException("User not found with username: " + userName));
        return Mapping.mapToUserResponseDto(user);
    }
    @Override
    public UserResponseDto getUserByEmail(String email) throws EntityNotFoundException {
        log.info("Fetching user by email: {}", email);
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));
        return Mapping.mapToUserResponseDto(user);
    }


    @Override
    public void deleteUserById(Long userId) throws EntityNotFoundException, EmptyEntityException{
        log.info("delete user by id : {}", userId);
        if (!userRepository.existsById(userId)) {
            throw new EntityNotFoundException("User not found with id: " + userId);
        }
        User user =userRepository.findById(userId).orElseThrow();
        user.setActive(false);
        UserRequestDto userDto = Mapping.mapToUserRequestDto(user);

        // Update the user details using the updateUser method
        updateUser(userDto);
        // Save the user with updated details and inactive status
        userRepository.save(user);

    }

    @Override
    public UserResponseDto updateUser(UserRequestDto userDto) throws EntityNotFoundException {
        User user = userRepository.findById(userDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userDto.getUserId()));


        user.setUserName(userDto.getUsername());
        user.setEmail(userDto.getEmail());

        User updatedUser = userRepository.save(user);
        return Mapping.mapToUserResponseDto(updatedUser);

    }


}
