package com.SweetHome.BookingService.service;


import com.SweetHome.BookingService.dto.PaymentInfo;
import com.SweetHome.BookingService.model.BookingInfoEntity;

public interface BookingService {

    public BookingInfoEntity savingBooking(BookingInfoEntity bookingInfo);
    public BookingInfoEntity takingPayment(int bookingId, PaymentInfo paymentInfo);
}
