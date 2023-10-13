package com.SweetHome.PaymentService.Service;

import com.SweetHome.PaymentService.model.TransactionDetailsEntity;

public interface TransactionService {

    public int creatingTransaction(TransactionDetailsEntity transactionDetails);
    public TransactionDetailsEntity gettingTransaction(int transactionId);
}
