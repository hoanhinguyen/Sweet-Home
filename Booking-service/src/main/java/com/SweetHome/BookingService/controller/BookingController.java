package com.SweetHome.BookingService.controller;

import com.SweetHome.BookingService.dto.PaymentInfo;
import com.SweetHome.BookingService.model.BookingInfoEntity;
import com.SweetHome.BookingService.service.BookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotel")
public class BookingController {

    @Autowired
    BookingServiceImpl bookingService;

//    saving the booking info and not for payment purpose
    @PostMapping(value = "/booking", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookingInfoEntity> createBooking(@RequestBody BookingInfoEntity bookingInfo) {

       BookingInfoEntity savedBooking = bookingService.savingBooking(bookingInfo);

       return new ResponseEntity<>(savedBooking, HttpStatus.CREATED);

    }

//  creating payment information
    @PostMapping(value = "booking/{bookingId}/transaction",  produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookingInfoEntity> requestPaymentService(@PathVariable(name = "bookingId") int bookingId , @RequestBody PaymentInfo paymentInfo) {

        BookingInfoEntity bookingInfo = bookingService.takingPayment(bookingId, paymentInfo);

        return new ResponseEntity<>(bookingInfo, HttpStatus.CREATED);
    }

}
