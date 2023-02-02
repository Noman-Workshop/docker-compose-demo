package com.eu.taxcalculation.calculation.exception;

public class CalculationNotFoundException extends Exception{
    private String message;

    public CalculationNotFoundException(String message){
        super(message);
        this.message=message;
    }

    public CalculationNotFoundException() {}
}