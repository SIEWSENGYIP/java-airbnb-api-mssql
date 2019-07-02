package com.example.airbnb.controllers;

import java.util.List;

import com.example.airbnb.entities.Booking;
import com.example.airbnb.repositories.BookingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path="/api")
public class BookingController {
    
    @Autowired
    BookingRepository bookingRepository;
	@GetMapping(path="/bookings", produces="application/json")
    public List<Booking> displayBookings() {
        return bookingRepository.getAllBookings();
    }

    @GetMapping(path="/properties/{id}/bookings", produces="application/json")
    public List<Booking> displayBookingByPropertyId(@PathVariable("id") int propertyId) {
        return bookingRepository.getBookingByPropertyId(propertyId);
    }

    @PostMapping(value="/bookings/{id}", produces="application/json")
    public Booking updateBooking(@RequestBody Booking booking, @PathVariable("id") int bookingId) {
        bookingRepository.updateBooking(booking, bookingId);
        return booking;
    }
}
