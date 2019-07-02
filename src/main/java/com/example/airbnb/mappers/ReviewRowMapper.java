package com.example.airbnb.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.airbnb.entities.Review;

import org.springframework.jdbc.core.RowMapper;

/**
 * ReviewRowMapper
 */
public class ReviewRowMapper implements RowMapper<Review> {
    @Override
    public Review mapRow(ResultSet row, int rowNum) throws SQLException {
       Review review = new Review();
       review.setId(row.getInt("id"));
       review.setRating(row.getInt("rating"));
       review.setRemark(row.getString("remark"));
       review.setPropertyId(row.getInt("property_id"));
       return review;
    }
}