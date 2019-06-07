package com.bobcurrie.flightreservation2.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter

@Entity
public class Passenger extends AbstractEntity {

    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String phone;

    @Override
    public String toString() {
        return "Passenger{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
