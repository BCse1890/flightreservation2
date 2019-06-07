package com.bobcurrie.flightreservation2.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ReservationUpdateRequest {

    // don't want to pass in entire reservation to update, just the following fields
    private Long id;
    private int numberOfBags;
    private Boolean checkedIn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumberOfBags() {
        return numberOfBags;
    }

    public void setNumberOfBags(int numberOfBags) {
        this.numberOfBags = numberOfBags;
    }

    public Boolean getCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(Boolean checkedIn) {
        this.checkedIn = checkedIn;
    }

    @Override
    public String toString() {
        return "ReservationUpdateRequest{" +
                "id=" + id +
                ", numberOfBags=" + numberOfBags +
                ", checkedIn=" + checkedIn +
                '}';
    }
}
