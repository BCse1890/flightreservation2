package com.bobcurrie.flightreservation2.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter

@Entity
public class User extends AbstractEntity {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
