package com.example.cinema.controllers;

import com.example.cinema.models.Cinema;
import com.example.cinema.models.Manager;
import com.example.cinema.models.Seat;


import com.example.cinema.services.SeatsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
public class SeatsController {

    SeatsService seatsService;

    @Autowired
    SeatsController(SeatsService seatsService) {
        this.seatsService = seatsService;
    }

    Cinema cinema = new Cinema(9, 9, 0, 81, 0);

    @GetMapping("/seats")
    public Cinema getSeats() {
        seatsService.countValue(cinema);
        return cinema;
    }

    @PostMapping("/purchase")
    public Seat getSeats(@RequestBody Seat seat) throws JsonProcessingException {
        seatsService.countValue(cinema);
        return seatsService.getChosenSeat(seat, cinema);
    }

    @PostMapping("/return")
    public String returnTicket(@RequestBody Seat seat) throws JsonProcessingException {
        seatsService.countValue(cinema);
        return seatsService.returnSeat(seat, cinema);
    }

    @PostMapping("/stats")
    public String showStats(@RequestBody Seat seat) throws JsonProcessingException {
        seatsService.countValue(cinema);
        return seatsService.showStats(seat, cinema,new Manager("qazwsxedc"));
    }

}
