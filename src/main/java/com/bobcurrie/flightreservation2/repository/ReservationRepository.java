package com.bobcurrie.flightreservation2.repository;


import com.bobcurrie.flightreservation2.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
