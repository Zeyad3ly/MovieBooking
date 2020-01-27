package com.example.moviebooking.data;

import java.util.ArrayList;

public class SessionModel {
    private ArrayList<Boolean> seats = new ArrayList<>();





    public void setSeats(ArrayList<Boolean> seats) {
        this.seats = seats;
    }

    public ArrayList<Boolean> getSeats() {
        if(seats.size() ==0) seats.ensureCapacity(8);
        return seats;
    }
}
