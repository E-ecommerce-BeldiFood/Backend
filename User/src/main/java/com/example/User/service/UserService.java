package com.example.User.service;

import com.example.User.entities.dto.UserRequestDto;
import com.example.User.entities.dto.UserResponseDto;
import com.example.User.exception.EmailAlreadyExistsException;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface UserService {
    public List<UserResponseDto> getAllUsers();
    public UserResponseDto createUser(UserRequestDto userDto) throws EmailAlreadyExistsException;
    public UserResponseDto getUserById(Long id) throws EntityNotFoundException;
    public UserResponseDto getUserByUserName(String userName) throws EntityNotFoundException;
    public void deleteUserById(Long id) throws EntityNotFoundException;
    public UserResponseDto updateUser( UserRequestDto userDto) throws EntityNotFoundException;
}

