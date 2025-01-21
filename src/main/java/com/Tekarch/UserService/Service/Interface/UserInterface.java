package com.Tekarch.UserService.Service.Interface;

import com.Tekarch.UserService.DTO.UserDto;

import java.util.List;

public interface UserInterface {
    UserDto registerUser(UserDto userDto);
    UserDto getUserById(Long userId);
    UserDto updateUser(Long userId, UserDto userDto);
    List<UserDto> getAllUsers();
}
