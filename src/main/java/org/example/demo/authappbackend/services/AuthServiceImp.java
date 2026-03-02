package org.example.demo.authappbackend.services;

import org.example.demo.authappbackend.dto.JwtResponse;
import org.example.demo.authappbackend.dto.UserDto;
import org.example.demo.authappbackend.entites.User;
import org.example.demo.authappbackend.repository.UserRepository;
import org.example.demo.authappbackend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImp implements AuthService{

    @Autowired
    private  UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDto registerUser(UserDto userDto) throws IllegalAccessException {

        UserDto userDto1 = userService.createUser(userDto);
        return userDto1;
    }

    @Override
    public UserDto loginUser(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BadCredentialsException("Invalid email or password"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Invalid email or password");
        }
        // Authentication
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwt = jwtUtil.generateToken(userDetails);
        // Optionally, you can return a JwtResponse object
        return UserDto.builder()
                .email(user.getEmail())
                .name(user.getName())
                .token(jwt)
                .build();
    }
}
