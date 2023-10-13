package com.SweetHome.BookingService.service;

import com.SweetHome.BookingService.dto.PaymentInfo;
import com.SweetHome.BookingService.exception.InvalidPaymentInfoException;
import com.SweetHome.BookingService.model.BookingInfoEntity;
import com.SweetHome.BookingService.repository.BookingRepository;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static com.SweetHome.BookingService.constants.ModeOfPayment.MODE_CARD;
import static com.SweetHome.BookingService.constants.ModeOfPayment.MODE_UPI;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RestTemplate restTemplate;


    @Value("${paymentApp.url}")
    private String postPaymentAppUrl;


    @Override
    public BookingInfoEntity savingBooking(BookingInfoEntity bookingInfo) {

        BookingInfoEntity bookingToSave = new BookingInfoEntity();

//      generating room numbers which will be sent back to the user
        ArrayList roomNumsResult = getRandomNumbers(bookingInfo.getNumOfRooms());
        String roomNums = String.join(",", roomNumsResult);

        Date currentDate = new Date(System.currentTimeMillis());

//      Room Price Calculation
        long fromDate = bookingInfo.getFromDate().getTime();
        long toDate = bookingInfo.getToDate().getTime();
        int numOfDays = (int) ((toDate - fromDate)/(1000 * 60 * 60 * 24));
        int price = 1000*bookingInfo.getNumOfRooms()*numOfDays;

//      Saving booking object to the database
        bookingToSave.setFromDate(bookingInfo.getFromDate());
        bookingToSave.setToDate(bookingInfo.getToDate());
        bookingToSave.setAadharNumber(bookingInfo.getAadharNumber());
        bookingToSave.setNumOfRooms(bookingInfo.getNumOfRooms());
        bookingToSave.setRoomPrice(price);
        bookingToSave.setRoomNumbers(roomNums);
        bookingToSave.setBookedOn(currentDate);

        BookingInfoEntity savedBooking = bookingRepository.save(bookingToSave);

        return savedBooking;
    }

    @Override
    public BookingInfoEntity takingPayment(int bookingId, PaymentInfo paymentInfo) {

        String inputPaymentMethod = paymentInfo.getPaymentMode();

//      exception will be thrown when the user send the wrong mode of payment
        if (!inputPaymentMethod.equalsIgnoreCase(MODE_UPI) && !inputPaymentMethod.equalsIgnoreCase(MODE_CARD)) {
            throw new InvalidPaymentInfoException(HttpStatus.BAD_REQUEST, "Invalid mode of payment");
        }

        BookingInfoEntity savedBooking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new InvalidPaymentInfoException(HttpStatus.BAD_REQUEST, " Invalid Booking Id"));

        PaymentInfo transaction = new PaymentInfo();
        transaction.setBookingId(bookingId);
        transaction.setPaymentMode(paymentInfo.getPaymentMode());
        transaction.setCardNumber(paymentInfo.getCardNumber());
        transaction.setUpiId(paymentInfo.getUpiId());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

//       send a post request to the payment service to get transaction info
        try {
            int transactionId = restTemplate.postForObject(postPaymentAppUrl, new HttpEntity<>(transaction, headers), Integer.class);
            savedBooking.setTransactionId(transactionId);

//          if the communication works, this message will be printed on the console.
            String message = "Booking confirmed for user with aadhaar number: "
                    + savedBooking.getAadharNumber()
                    +    "    |    "
                    + "Here are the booking details:    " + savedBooking.toString();

            System.out.println(message);

        } catch (RestClientException e) {

            throw  new InvalidPaymentInfoException(HttpStatus.BAD_REQUEST, " Invalid Booking Id ");

        }

        BookingInfoEntity updatedBookingWithTransactionId = bookingRepository.save(savedBooking);

        return updatedBookingWithTransactionId;
    }

    public static ArrayList<String> getRandomNumbers(int count){
        Random rand = new Random();
        int upperBound = 100;
        ArrayList<String>numberList = new ArrayList<String>();

        for (int i=0; i<count; i++){
            numberList.add(String.valueOf(rand.nextInt(upperBound)));
        }

        return numberList;
    }


}
