package org.example.demo.authappbackend.controller;

import org.example.demo.authappbackend.dto.UserDto;
import org.example.demo.authappbackend.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vi/auth")
public class AuthController {

  @Autowired
  private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) throws IllegalAccessException {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.registerUser(userDto));

    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserDto userDto) {
        try {
            UserDto loggedInUser = authService.loginUser(userDto.getEmail(), userDto.getPassword());
            return ResponseEntity.ok(loggedInUser);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
        }
    }
}
