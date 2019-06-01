package com.bobcurrie.flightreservation2.repository;


import com.bobcurrie.flightreservation2.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}
