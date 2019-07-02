package com.example.airbnb.controllers;

import java.util.List;

import com.example.airbnb.entities.Review;
import com.example.airbnb.repositories.ReviewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path="/api")
public class ReviewController {
    
    @Autowired
    ReviewRepository reviewRepository;
	@GetMapping(path="/reviews", produces="application/json")
    public List<Review> displayReviews() {
        return reviewRepository.getAllReviews();
    }


    @GetMapping(path="/properties/{id}/reviews", produces="application/json")
    public List<Review> displayReviewByPropertyId(@PathVariable("id") int propertyId) {
        return reviewRepository.getReviewByPropertyId(propertyId);
    }

    @GetMapping(path="/reviews_param", produces="application/json")
    public List<Review> displayReviewByRating(@RequestParam int rating) {
        return reviewRepository.getReviewByRating(rating);
    }

    @PostMapping(value="/properties/{id}/reviews", produces="application/json")
    public Review createReview(@RequestBody Review review, @PathVariable("id") int propertyId) {
        reviewRepository.createReview(review, propertyId);
        return review;
    }
}
