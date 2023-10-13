package com.SweetHome.PaymentService.Service;

import com.SweetHome.PaymentService.model.TransactionDetailsEntity;
import com.SweetHome.PaymentService.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    PaymentRepository paymentRepository;

    @Override
    public int creatingTransaction(TransactionDetailsEntity transactionDetails) {

        TransactionDetailsEntity transactionToSave = new TransactionDetailsEntity();

        transactionToSave.setBookingId(transactionDetails.getBookingId());
        transactionToSave.setCardNumber(transactionDetails.getCardNumber());
        transactionToSave.setUpiId(transactionDetails.getUpiId());
        transactionToSave.setPaymentMode(transactionDetails.getPaymentMode());

//      getting the transaction object after saving it in the database
        TransactionDetailsEntity savedTransaction = paymentRepository.save(transactionToSave);

        return savedTransaction.getId();
    }

    @Override
    public TransactionDetailsEntity gettingTransaction(int transactionId) {

        TransactionDetailsEntity transactionDetails = paymentRepository.findById(transactionId).get();

        return transactionDetails;
    }
}
