package com.SweetHome.BookingService.repository;

import com.SweetHome.BookingService.model.BookingInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<BookingInfoEntity, Integer> {
}
