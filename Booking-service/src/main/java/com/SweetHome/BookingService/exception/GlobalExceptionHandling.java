package com.SweetHome.BookingService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandling extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidPaymentInfoException.class)
    public ResponseEntity<ErrorDetails> handleBlogAPIException(InvalidPaymentInfoException exception, WebRequest webRequest) {
//      errorDetails object will be sent back the user when this exception is thrown
        ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(), exception.getStatus().value());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
