package com.example.cinema.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seat {


    public Seat(UUID token,int row, int column, int price) {
        this.token = token;
        this.row = row;
        this.column = column;
        this.price = price;
    }


    private UUID token;
    private int row;
    private int column;
    private int price;
    @JsonIgnore
    private boolean isTaken = false;


}
