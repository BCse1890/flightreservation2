package com.bobcurrie.flightreservation2.controllers;

import com.bobcurrie.flightreservation2.dto.ReservationUpdateRequest;
import com.bobcurrie.flightreservation2.model.Reservation;
import com.bobcurrie.flightreservation2.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// add CrossOrigin annotation so can connect  Angular app with Restful services
@RestController
@CrossOrigin
public class ReservationRestContrroller {

    @Autowired
    ReservationRepository reservationRepository;

    @RequestMapping("/reservations/{id}")
    // @Pathvariable binds the path variable (ie id) to the jave method variable Long id
    public Reservation findReservation(@PathVariable("id") Long id) {
        return reservationRepository.getOne(id);
    }

    @RequestMapping("/reservations")
    // use @RequestBody to tell Spring that ReservationUpdateRequest object to be constructed at runtime
    // using information in the request (ie JSON)
    public Reservation updateReservation(@RequestBody ReservationUpdateRequest request) {

        Reservation reservation = reservationRepository.getOne(request.getId());
        reservation.setNumberOfBags(request.getNumberOfBags());
        reservation.setCheckedIn(request.getCheckedIn());
        return reservationRepository.save(reservation);
    }
}
