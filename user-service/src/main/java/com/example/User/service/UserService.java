package com.example.User.service;

import com.example.User.dto.UserRequestDto;
import com.example.User.dto.UserResponseDto;
import com.example.User.exception.EmailAlreadyExistsException;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface UserService {
    public List<UserResponseDto> getAllUsers();
    public UserResponseDto createUser(UserRequestDto userDto) throws EmailAlreadyExistsException;
    public UserResponseDto getUserById(Long userId) throws EntityNotFoundException;
    public UserResponseDto getUserByUserName(String userName) throws EntityNotFoundException;
    UserResponseDto getUserByEmail(String email) throws EntityNotFoundException;
    public void deleteUserById(Long userId) throws EntityNotFoundException;
    public UserResponseDto updateUser( UserRequestDto userDto) throws EntityNotFoundException;
}

