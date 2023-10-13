package com.SweetHome.PaymentService.controller;

import com.SweetHome.PaymentService.Service.TransactionService;
import com.SweetHome.PaymentService.model.TransactionDetailsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping(value = "/transaction",  produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> createTransactionId(@RequestBody TransactionDetailsEntity transactionDetails) {

        int transactionId = transactionService.creatingTransaction(transactionDetails);

        return new ResponseEntity<>(transactionId, HttpStatus.CREATED);

    }


    @GetMapping("/transaction/{transactionId}")
    public ResponseEntity<TransactionDetailsEntity> getTransactionDetails(@PathVariable(name = "transactionId") int transactionId) {

        TransactionDetailsEntity transactionDetails = transactionService.gettingTransaction(transactionId);

        return ResponseEntity.ok(transactionDetails);
    }
}
