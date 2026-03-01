package org.example.demo.authappbackend.controller;

import org.example.demo.authappbackend.dto.MessageResponse;
import org.example.demo.authappbackend.dto.UserDto;

import org.example.demo.authappbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createUser (@RequestBody UserDto userDto) throws IllegalAccessException {
        userService.createUser(userDto);
        return ResponseEntity.ok(new MessageResponse("the user register"));
    }
}
