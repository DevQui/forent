package com.springboot.forent.controller;

import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.forent.model.Reviews;
import com.springboot.forent.service.ReviewsService;

@RestController
public class ReviewsController {
	@Autowired
    private ReviewsService reviewsService;	
	
	@GetMapping("/reviews")
    public List<Reviews> listAllReviews() {
        return reviewsService.listAllReviews();
    }
	
	@GetMapping("/reviews/{id_review}")
    public Reviews getReview(@PathVariable Integer id_review) {
        return reviewsService.getReview(id_review);
    }
	
	@GetMapping("/properties/{id_property}/reviews")
    public List<Reviews> listAllPropertyReviews(@PathVariable Integer id_property) {
        return reviewsService.listAllPropertyReviews(id_property);
    }
		
	@GetMapping("/properties/{id_property}/reviews/{id}")
    public Reviews getPropertyReview(@PathVariable Integer id_property, @PathVariable Integer id) {
        return reviewsService.getPropertyReview(id_property, id);    
	}
	
	@PostMapping("/users/{id_user}/properties/{id_property}/reviews")
	public ResponseEntity<String> add(@PathVariable Integer id_user, @PathVariable Integer id_property, @RequestBody Reviews review) throws URISyntaxException {		
		return reviewsService.saveReview(id_user, id_property,review);
    }
	
    @DeleteMapping("/users/{id_user}/properties/{id_property}/reviews/{id_review}")
    public ResponseEntity<String> delete(@PathVariable Integer id_property, @PathVariable Integer id_review, @PathVariable Integer id_user) {
        return reviewsService.deleteReview(id_property, id_review, id_user);
    }
    
    @DeleteMapping("/users/{id_user}/properties/{id_property}/reviews")
    public ResponseEntity<String> deleteReview(@PathVariable Integer id_property, @RequestParam Integer id_review, @PathVariable Integer id_user) {
        return reviewsService.deleteReview(id_property, id_review, id_user);
    }
    
    @GetMapping("users/{id_user}/reviews")
    public List<Reviews> listAllReviewsOfUser(@PathVariable Integer id_user) {
        return reviewsService.listAllReviewsOfUser(id_user);
    }
	
	@GetMapping("users/{id_user}/reviews/{id_review}")
    public Reviews getReviewOfUser(@PathVariable Integer id_user, @PathVariable Integer id_review) {
        return reviewsService.getReviewOfUser(id_user, id_review);
    }
	
}
