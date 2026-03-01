package org.example.demo.authappbackend.services;

import org.example.demo.authappbackend.dto.UserDto;
import org.example.demo.authappbackend.entites.Provider;
import org.example.demo.authappbackend.entites.User;
import org.example.demo.authappbackend.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) throws IllegalAccessException {
        if (userDto.getEmail().isBlank() || userDto.getEmail()== null){
            throw new IllegalAccessException("Email is required");
        }
        if (userRepository.equals(userDto.getEmail())){
            throw new IllegalAccessException("Email is Already exist");
        }
      User user =  modelMapper.map(userDto , User.class);


        return null;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        return null;
    }

    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        return null;
    }

    @Override
    public void deleteUser(String userId) {

    }

    @Override
    public UserDto getUserById(String userId) {
        return null;
    }

    @Override
    public Iterable<UserDto> getAllUsers() {
        return null;
    }

//    @Override
//    public void createUser(UserDto userDto) throws IllegalAccessException {
//
//        if (userDto.getEmail() == null || userDto.getEmail().isBlank()){
//            throw new IllegalAccessException("Email. is required");
//        }
//
//        if (userRepository.equals(userDto.getEmail())){
//            throw   new IllegalAccessException("Email is already exist");
//        }
//
//        User user = modelMapper.map(userDto , User.class);
//
//        user.setProvider(userDto.getProvider()!= null ? userDto.getProvider() : Provider.LOCAL);
//
//        userRepository.save(user);
//
//    }
}
