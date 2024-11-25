package com.japhni81.jani.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.japhni81.jani.models.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long>{

}
