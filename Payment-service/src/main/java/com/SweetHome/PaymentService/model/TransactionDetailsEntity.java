package com.SweetHome.PaymentService.model;


import jakarta.persistence.*;

@Entity
@Table(name = "transaction")
public class TransactionDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transactionId")
    private int id;

    @Column(name = "paymentMode")
    private String paymentMode;

    @Column(name = "booking Id", nullable = false)
    private int bookingId;

    @Column(name = "upi Id", nullable = true)
    private String upiId;

    @Column(name = "cardNumber", nullable = true)
    private String cardNumber;

    public TransactionDetailsEntity() {
    }

    public TransactionDetailsEntity(int id, String paymentMode, int bookingId, String upiId, String cardNumber) {
        this.id = id;
        this.paymentMode = paymentMode;
        this.bookingId = bookingId;
        this.upiId = upiId;
        this.cardNumber = cardNumber;
    }

    public TransactionDetailsEntity(String paymentMode, int bookingId, String upiId, String cardNumber) {
        this.paymentMode = paymentMode;
        this.bookingId = bookingId;
        this.upiId = upiId;
        this.cardNumber = cardNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getUpiId() {
        return upiId;
    }

    public void setUpiId(String upiId) {
        this.upiId = upiId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}