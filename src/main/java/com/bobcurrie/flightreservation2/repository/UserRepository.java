package com.bobcurrie.flightreservation2.repository;


import com.bobcurrie.flightreservation2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
