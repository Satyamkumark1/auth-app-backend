package org.example.demo.authappbackend.services;

import org.example.demo.authappbackend.dto.UserDto;

public interface AuthService {
    UserDto registerUser(UserDto userDto) throws IllegalAccessException;
    UserDto loginUser(String email, String password);
}
