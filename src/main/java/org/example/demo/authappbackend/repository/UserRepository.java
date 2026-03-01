package org.example.demo.authappbackend.repository;

import org.example.demo.authappbackend.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User,Long> {
}
