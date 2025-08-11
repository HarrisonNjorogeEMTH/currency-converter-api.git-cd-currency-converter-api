package com.example.demo.dto;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
public class ConversionDTO {
    private String fromCurrency;
    private String toCurrency;
    private double amount;
    private double convertedAmount;
    private double rate;
    private LocalDateTime dateTime;


    public ConversionDTO(String from, String to, double amount, double convertedAmount, double rate) {
    }
}
