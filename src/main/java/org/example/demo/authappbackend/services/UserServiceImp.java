package org.example.demo.authappbackend.services;

import jakarta.transaction.Transactional;
import org.example.demo.authappbackend.dto.UserDto;
import org.example.demo.authappbackend.entites.Provider;
import org.example.demo.authappbackend.entites.User;
import org.example.demo.authappbackend.repository.UserRepository;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) throws IllegalAccessException {
        if (userDto.getEmail().isBlank() || userDto.getEmail() == null) {
            throw new IllegalAccessException("Email is required");
        }
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new IllegalAccessException("Email is Already exist");
        }
        User user = modelMapper.map(userDto, User.class);
        user.setProvider(userDto.getProvider() != null ? userDto.getProvider() : Provider.LOCAL);
        User savedUser = userRepository.save(user);

        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        User existingUser = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        if (userDto.getName() != null)
            existingUser.setName(userDto.getName());
        if (userDto.getImage() != null)
            existingUser.setImage(userDto.getImage());
        if (userDto.getGender() != null)
            existingUser.setGender(userDto.getGender());
        if (userDto.getPassword() != null)
            existingUser.setPassword(userDto.getPassword());

        User updatedUser = userRepository.save(existingUser);
        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(UUID.fromString(userId));
    }

    @Override
    public UserDto getUserById(String userId) {
        User user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    @Transactional
    public Iterable<UserDto> getAllUsers() {
        return userRepository
                .findAll().stream().map(user -> modelMapper.map(user, UserDto.class)).toList();
    }

    // @Override
    // public void createUser(UserDto userDto) throws IllegalAccessException {
    //
    // if (userDto.getEmail() == null || userDto.getEmail().isBlank()){
    // throw new IllegalAccessException("Email. is required");
    // }
    //
    // if (userRepository.equals(userDto.getEmail())){
    // throw new IllegalAccessException("Email is already exist");
    // }
    //
    // User user = modelMapper.map(userDto , User.class);
    //
    // user.setProvider(userDto.getProvider()!= null ? userDto.getProvider() :
    // Provider.LOCAL);
    //
    // userRepository.save(user);
    //
    // }
}
