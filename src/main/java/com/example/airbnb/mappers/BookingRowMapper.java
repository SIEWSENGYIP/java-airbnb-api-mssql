package com.example.airbnb.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.airbnb.entities.Booking;

import org.springframework.jdbc.core.RowMapper;

/**
 * BookingRowMapper
 */
public class BookingRowMapper implements RowMapper<Booking> {
    @Override
    public Booking mapRow(ResultSet row, int rowNum) throws SQLException {
       Booking booking = new Booking();
       booking.setId(row.getInt("id"));
       booking.setCheckInDate(row.getString("checkInDate"));
       booking.setCheckOutDate(row.getString("checkOutDate"));
       booking.setTotalPrice(row.getInt("totalPrice"));
       booking.setRemarks(row.getString("remarks"));
       booking.setNumGuest(row.getInt("numGuest"));
       booking.setUserId(row.getInt("user_id"));
       booking.setPropertyId(row.getInt("property_id"));
       return booking;
    }
}