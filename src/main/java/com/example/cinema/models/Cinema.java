package com.example.cinema.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cinema {
    private int total_rows;
    private int total_columns;
    private int current_income;
    private int number_of_available_seats;
    private int number_of_purchased_tickets;
    private ArrayList<Seat> available_seats;


    public Cinema(int total_rows, int total_columns, int current_income,
                  int number_of_available_seats, int number_of_purchased_tickets) {
        this.total_rows = total_rows;
        this.total_columns = total_columns;
        this.current_income = current_income;
        this.number_of_available_seats = number_of_available_seats;
        this.number_of_purchased_tickets = number_of_purchased_tickets;
        this.available_seats = new ArrayList<>();


    }


}
