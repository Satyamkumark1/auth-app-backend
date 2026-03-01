package org.example.demo.authappbackend.services;

import org.example.demo.authappbackend.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDto createUser(UserDto userDto) throws IllegalAccessException;

    UserDto getUserByEmail(String email);

    UserDto updateUser(UserDto userDto , String userId);

    void  deleteUser(String userId);

    UserDto getUserById(String userId);

    Iterable<UserDto> getAllUsers();
}
