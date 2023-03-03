package com.example.cinema.services;

import com.example.cinema.models.Cinema;
import com.example.cinema.models.Manager;
import com.example.cinema.models.Seat;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.UUID;

@Service
public class SeatsService {

    private static final String SUPER_SECRET_PASSWORD = "qazwsxedc";

    public void countValue(Cinema cinema) {
        Seat seat;

        for (int i = 1; i <= cinema.getTotal_rows(); i++) {
            for (int j = 1; j <= cinema.getTotal_columns(); j++) {
                if (i <= 4) {
                    seat = new Seat(UUID.randomUUID(), i, j, 10);

                } else {
                    seat = new Seat(UUID.randomUUID(), i, j, 8);

                }
                cinema.getAvailable_seats().add(seat);

            }
        }
    }

    public Seat getChosenSeat(Seat seat, Cinema cinema) throws JsonProcessingException {
        //if user wanna purchase seat which doesn't exist we throw an Exception
        if (seat.getRow() > cinema.getTotal_rows() || seat.getColumn() > cinema.getTotal_columns() ||
                seat.getRow() <= 0 || seat.getColumn() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "error : \"The number of a row or a column is out of bounds!\"");
        }
        for (Seat seat1 : cinema.getAvailable_seats()) {
            if (seat1.getRow() == seat.getRow() && seat1.getColumn() == seat.getColumn()) {
                if (seat1.isTaken()) {
                    break;
                }

                cinema.setNumber_of_available_seats(cinema.getNumber_of_available_seats() - 1);
                cinema.setCurrent_income(cinema.getCurrent_income() + seat1.getPrice());
                cinema.setNumber_of_purchased_tickets(cinema.getNumber_of_purchased_tickets() + 1);
                System.out.println("BOUGHT");
                seat1.setTaken(true);
                return seat1;

            }
        }
        throw new ResponseStatusException(HttpStatus.IM_USED,
                "\"error\": \"The ticket has been already purchased!\"");
    }

    public String returnSeat(Seat seat, Cinema cinema) throws JsonProcessingException {
        for (Seat seat1 : cinema.getAvailable_seats()) {
            if (seat1.getToken() == seat.getToken() || seat1.getToken().equals(seat.getToken())) {
                cinema.setNumber_of_available_seats(cinema.getNumber_of_available_seats() + 1);
                cinema.setCurrent_income(cinema.getCurrent_income() - seat1.getPrice());
                cinema.setNumber_of_purchased_tickets(cinema.getNumber_of_purchased_tickets() - 1);
                seat1.setTaken(false);
                return "You have succesfully return your ticket!!!";
            }
        }

        return null;

    }

    public String showStats(Seat seat, Cinema cinema, Manager manager) throws JsonProcessingException {

        if (manager.getPassword().equals(SUPER_SECRET_PASSWORD)) {
            return String.format("{ \"current_income:\" %d, \t " +
                            "\"number_of_available_seats:\" %d, \t " +
                            "\"number_of_purchased_tickets:\" %d, \t   }", cinema.getCurrent_income(),
                    cinema.getNumber_of_available_seats(),
                    cinema.getNumber_of_purchased_tickets());
        }


        return null;

    }


}