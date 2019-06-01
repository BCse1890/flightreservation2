package com.bobcurrie.flightreservation2.services;

import com.bobcurrie.flightreservation2.dto.ReservationRequest;
import com.bobcurrie.flightreservation2.model.Flight;
import com.bobcurrie.flightreservation2.model.Passenger;
import com.bobcurrie.flightreservation2.model.Reservation;
import com.bobcurrie.flightreservation2.repository.FlightRepository;
import com.bobcurrie.flightreservation2.repository.PassengerRepository;
import com.bobcurrie.flightreservation2.repository.ReservationRepository;
import com.bobcurrie.flightreservation2.util.EmailUtil;
import com.bobcurrie.flightreservation2.util.PDFGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    PDFGenerator pdfGenerator;

    @Autowired
    EmailUtil emailUtil;

    @Override
    public Reservation bookFlight(ReservationRequest request) {

        // make payment
        Long flightId = request.getFlightId();
        Flight flight = flightRepository.getOne(flightId);
        Passenger passenger = new Passenger();
        passenger.setFirstName(request.getPassengerFirstName());
        passenger.setLastName(request.getPassengerLastName());
        passenger.setPhone(request.getPassengerPhone());
        passenger.setEmail(request.getPassengerEmail());
        Passenger savedPassenger = passengerRepository.save(passenger);

        Reservation reservation = new Reservation();
        reservation.setFlight(flight);
        reservation.setPassenger(savedPassenger);
        reservation.setCheckedIn(false);

        Reservation savedReservation = reservationRepository.save(reservation);

        String filePath = "/Users/Lenovo/JavaProjects/reservations/reservation"
                + savedReservation.getId() + ".pdf";
        pdfGenerator.generateItinary(savedReservation,
                filePath);
        emailUtil.sendItinerary(passenger.getEmail(), filePath);

        return savedReservation;
    }
}
