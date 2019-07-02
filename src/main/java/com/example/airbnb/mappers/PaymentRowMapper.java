package com.example.airbnb.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.airbnb.entities.Payment;

import org.springframework.jdbc.core.RowMapper;

/**
 * PaymentRowMapper
 */
public class PaymentRowMapper implements RowMapper<Payment> {
    @Override
    public Payment mapRow(ResultSet row, int rowNum) throws SQLException {
       Payment payment = new Payment();
       payment.setId(row.getInt("id"));
       payment.setAmount(row.getInt("amount"));
       payment.setStatus(row.getString("status"));
       payment.setPaymentMethod(row.getString("paymentMethod"));
       payment.setBookingId(row.getInt("bookingId"));
       return payment;
    }
}