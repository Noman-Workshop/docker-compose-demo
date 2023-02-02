package com.eu.taxcalculation.payment.exception;

public class PaymentNotFoundException extends Exception{
    private String message;

    public PaymentNotFoundException(String message){
        super(message);
        this.message=message;
    }

    public PaymentNotFoundException() {}
}
