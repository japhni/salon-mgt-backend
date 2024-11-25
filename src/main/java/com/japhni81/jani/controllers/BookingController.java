package com.japhni81.jani.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.japhni81.jani.models.Booking;
import com.japhni81.jani.services.BookingService;

@RestController
public class BookingController {

	@Autowired
	BookingService bookingService;

	@PostMapping("/create-booking")
	public ResponseEntity<Booking> saveBooking(@RequestBody Booking booking) {

		return new ResponseEntity<Booking>(bookingService.createBooking(booking), HttpStatus.CREATED);
	}

	@GetMapping("/all-bookings")
	public ResponseEntity<List<Booking>> getAllBookings() {

		return new ResponseEntity<List<Booking>>(bookingService.getAllBookings(), HttpStatus.OK);
	}

	@GetMapping("/read-booking-by-id/{id}")
	public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {

		return new ResponseEntity<Booking>(bookingService.getBookingById(id), HttpStatus.OK);
	}

	@PutMapping("/update-booking/{id}")
	public ResponseEntity<Booking> updateBooking(@PathVariable Long id, @RequestBody Booking booking) {

		booking.setId(id);

		return new ResponseEntity<Booking>(bookingService.createBooking(booking), HttpStatus.OK);
	}

	@DeleteMapping("/delete-booking/{id}")
	public ResponseEntity<HttpStatus> deleteBooking(@PathVariable Long id) {

		bookingService.deleteBooking(id);

		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);

	}

}
