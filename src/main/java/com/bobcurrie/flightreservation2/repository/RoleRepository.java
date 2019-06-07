package com.bobcurrie.flightreservation2.repository;

import com.bobcurrie.flightreservation2.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
