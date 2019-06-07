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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    private static final Logger LOGGER =
            LoggerFactory.getLogger(ReservationServiceImpl.class);

    @Value("${com.bobcurrie.flightreservation2.itinerary.dirpath}")
    private String ITINERARY_DIR;

    @Override
    public Reservation bookFlight(ReservationRequest request) {

        LOGGER.info("Inside bookFlight()");

        // make payment
        Long flightId = request.getFlightId();
        LOGGER.info("Fetching  flight for flight id:" + flightId);
        Flight flight = flightRepository.getOne(flightId);
        Passenger passenger = new Passenger();
        passenger.setFirstName(request.getPassengerFirstName());
        passenger.setLastName(request.getPassengerLastName());
        passenger.setPhone(request.getPassengerPhone());
        passenger.setEmail(request.getPassengerEmail());
        LOGGER.info("Saving the passenger:" + passenger);
        Passenger savedPassenger = passengerRepository.save(passenger);

        Reservation reservation = new Reservation();
        reservation.setFlight(flight);
        reservation.setPassenger(savedPassenger);
        reservation.setCheckedIn(false);

        Reservation savedReservation = reservationRepository.save(reservation);


        String filePath = ITINERARY_DIR
                + savedReservation.getId() + ".pdf";
        LOGGER.info("Generating  the itinerary");
        pdfGenerator.generateItinary(savedReservation,
                filePath);
        LOGGER.info("Emailing the Itinerary");
        emailUtil.sendItinerary(passenger.getEmail(), filePath);

        return savedReservation;
    }
}
