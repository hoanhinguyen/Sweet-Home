package com.SweetHome.BookingService.exception;

import org.springframework.http.HttpStatus;

// custom exception for invalid payment information from the request
public class InvalidPaymentInfoException extends RuntimeException{
    private HttpStatus status;
    private String message;

    public InvalidPaymentInfoException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public InvalidPaymentInfoException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
