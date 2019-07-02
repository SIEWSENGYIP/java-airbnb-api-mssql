package com.example.airbnb.repositories;

import java.util.List;

import com.example.airbnb.entities.Review;
import com.example.airbnb.mappers.ReviewRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * ReviewRepository
 */

 @Transactional
 @Repository
public class ReviewRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ReviewRepository(JdbcTemplate jdbcTemplate) {
      this.jdbcTemplate = jdbcTemplate;
    }

    public List<Review> getAllReviews() {
        String sql = "SELECT * FROM review";
        RowMapper<Review> rowMapper = new ReviewRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    } 

    public List<Review> getReviewByPropertyId(int id) {
      String sql = "SELECT * FROM review WHERE property_id = ?";
      RowMapper<Review> rowMapper = new ReviewRowMapper();
      return this.jdbcTemplate.query(sql, new Object[]{id}, rowMapper);

    }

    public List<Review> getReviewByRating(int rating) {
      String sql = "SELECT * FROM review WHERE rating > ?";
      RowMapper<Review> rowMapper = new ReviewRowMapper();
      return this.jdbcTemplate.query(sql, new Object[]{rating}, rowMapper);

    }

    public void createReview(Review review, int id) {
      String sql = "INSERT INTO review VALUES(?, ?, ?) ";
      int rating = review.getRating();
      String remark = review.getRemark();
      this.jdbcTemplate.update(sql, rating, remark, id);
    
      sql = "SELECT TOP 1 * FROM review ORDER BY id DESC ";
      RowMapper<Review> rowMapper = new ReviewRowMapper();
      List<Review> reviews = this.jdbcTemplate.query(sql, rowMapper);
    
          review.setId(reviews.get(0).getId());
          review.setPropertyId(id);
    }
}