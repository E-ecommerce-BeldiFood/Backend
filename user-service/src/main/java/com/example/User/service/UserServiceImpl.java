package com.example.User.service;

import com.example.User.dto.UserRequestDto;
import com.example.User.dto.UserResponseDto;
import com.example.User.entities.User;
import com.example.User.exception.EmailAlreadyExistsException;
import com.example.User.exception.EmptyEntityException;
import com.example.User.mappers.Mapping;
import com.example.User.repository.UserRepository;
import com.example.User.utils.UserInputValidation;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{
    private  final UserRepository userRepository;
    private final UserInputValidation userInputValidation;

    private final PasswordEncoder passwordEncoder;



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
        log.info("Creating new user with email : {}", userDto.getEmail());

        if (!UserInputValidation.isValidUsername(userDto.getUserName())) {
            throw new IllegalArgumentException("UserName is required");
        }
        if (!UserInputValidation.isValidFirstName(userDto.getFirstName())) {
            throw new IllegalArgumentException("FirstName is required");
        }
        if (!UserInputValidation.isValidLastName(userDto.getLastName())) {
            throw new IllegalArgumentException("LastName is required");
        }
        if (!UserInputValidation.isValidEmail(userDto.getEmail())) {
            throw new IllegalArgumentException("Invalid email format");
        }

        // Check if email already exists
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists: " + userDto.getEmail());
        }

        User user = Mapping.mapToUserEntity(userDto);
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return Mapping.mapToUserResponseDto(userRepository.save(user));
    }

    @Override
    public UserResponseDto getUserByUserName(String userName) throws EntityNotFoundException {
        log.info("Fetching user by userName: {}", userName);
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new EntityNotFoundException("User not found with userName: " + userName));
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
    public void deleteUserById(Long id) throws EntityNotFoundException, EmptyEntityException{
        log.info("delete user by id : {}", id);
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found with id: " + id);
        }
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

    @Override
    public User getUserByLogin(String login) throws EntityNotFoundException {
        return userRepository.findByLogin(login).orElseThrow(() -> new EntityNotFoundException("User not found with Login: " + login));
    }



}
