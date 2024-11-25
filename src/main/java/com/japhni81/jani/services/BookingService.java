package com.japhni81.jani.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.japhni81.jani.models.Booking;
import com.japhni81.jani.repositories.BookingRepository;

@Service
public class BookingService {

	@Autowired
	BookingRepository bookingRepository;

	public Booking createBooking(Booking booking) {

		return bookingRepository.save(booking);
	}

	public List<Booking> getAllBookings() {

		return bookingRepository.findAll();
	}

	public Booking getBookingById(Long id) {

		Optional<Booking> booking = bookingRepository.findById(id);

		if (booking.isPresent()) {

			return booking.get();
		}
		throw new RuntimeException("The Booking with id %d is not available".formatted(id));
	}

	public void deleteBooking(Long id) {

		bookingRepository.deleteById(id);
	}

}
