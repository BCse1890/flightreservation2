package com.bobcurrie.flightreservation2.services;

import com.bobcurrie.flightreservation2.dto.ReservationRequest;
import com.bobcurrie.flightreservation2.model.Reservation;

public interface ReservationService {

    public Reservation bookFlight(ReservationRequest request);
}
