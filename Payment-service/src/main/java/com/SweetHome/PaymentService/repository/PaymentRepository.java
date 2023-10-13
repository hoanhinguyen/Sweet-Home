package com.SweetHome.PaymentService.repository;

import com.SweetHome.PaymentService.model.TransactionDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<TransactionDetailsEntity, Integer> {
}
