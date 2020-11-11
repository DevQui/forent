package com.springboot.forent.controller;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.forent.model.Reviews;
import com.springboot.forent.service.ReviewsService;

@RestController
public class ReviewsController {
	@Autowired
    private ReviewsService reviewsService;
	
	@GetMapping("/reviews")
    public List<Reviews> list() {
        return reviewsService.listAllReviews();
    }
	
	@GetMapping("/reviews/{id}")
    public ResponseEntity<Reviews> get(@PathVariable Integer id) {
        try {
            Reviews reviews = reviewsService.getReview(id);
            return new ResponseEntity<Reviews>(reviews, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Reviews>(HttpStatus.NOT_FOUND);
        }
	}
	
	@PostMapping("/reviews")
	public void add(@RequestBody Reviews review) {
		OffsetDateTime current = OffsetDateTime.now();
		String created_datetime = current.toString();	
		review.setCreated_datetime(created_datetime);
		reviewsService.saveReview(review);
    }
	
	
    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        String response = reviewsService.deleteReview(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
