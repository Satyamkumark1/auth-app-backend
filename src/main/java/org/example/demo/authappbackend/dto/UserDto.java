package org.example.demo.authappbackend.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.demo.authappbackend.entites.Provider;
import org.example.demo.authappbackend.entites.Role;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {


    private UUID id;
    private String name;
    private String email;
    private String password;
    private String image;
    // private boolean enable = true;
    private Instant createdAt  = Instant.now();
    private Instant updatedAt  = Instant.now();
    private String gender;
    private Provider provider = Provider.LOCAL;
    private Set<Role> roles= new HashSet<>();
}
