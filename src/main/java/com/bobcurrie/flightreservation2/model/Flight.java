package com.bobcurrie.flightreservation2.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter

@Entity
public class Flight extends AbstractEntity {

    private String flightNumber;
    private String operatingAirlines;
    private String arrivalCity;
    private String departureCity;
    private Date dateOfDeparture;
    private Timestamp estimatedDepartureTime;

}
