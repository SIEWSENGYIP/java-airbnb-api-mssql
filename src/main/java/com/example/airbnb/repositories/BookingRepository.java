package com.example.airbnb.repositories;

import java.util.List;

import com.example.airbnb.entities.Booking;
import com.example.airbnb.mappers.BookingRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * BookingRepository
 */

 @Transactional
 @Repository
public class BookingRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookingRepository(JdbcTemplate jdbcTemplate) {
      this.jdbcTemplate = jdbcTemplate;
    }

    public List<Booking> getAllBookings() {
        String sql = "SELECT * FROM booking";
        RowMapper<Booking> rowMapper = new BookingRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    } 

    public List<Booking> getBookingByPropertyId(int id) {
      String sql = "SELECT * FROM booking WHERE property_id = ?";
      RowMapper<Booking> rowMapper = new BookingRowMapper();
      return this.jdbcTemplate.query(sql, new Object[]{id}, rowMapper);

    }

    public void updateBooking(Booking booking, int id) {
      String sql = "UPDATE booking SET totalPrice = ? WHERE id = ? ";
      int totalPrice = booking.getTotalPrice();
      this.jdbcTemplate.update(sql, totalPrice, id);

      sql = "SELECT * FROM booking WHERE id = ?";
      RowMapper<Booking> rowMapper = new BookingRowMapper();
      List<Booking> bookings = this.jdbcTemplate.query(sql, new Object[]{id}, rowMapper);
    
      booking.setId(id);
      booking.setCheckInDate(bookings.get(0).getCheckInDate());
      booking.setCheckOutDate(bookings.get(0).getCheckOutDate());
      booking.setNumGuest(bookings.get(0).getNumGuest());
      booking.setPropertyId(bookings.get(0).getPropertyId());
      booking.setRemarks(bookings.get(0).getRemarks());
      booking.setUserId(bookings.get(0).getUserId());
    }
}