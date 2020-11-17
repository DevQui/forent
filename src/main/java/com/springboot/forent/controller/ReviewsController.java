package com.springboot.forent.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
		
	@GetMapping("/properties/{id_property}/reviews")
    public List<Reviews> list(@PathVariable Integer id_property) {
        return (List<Reviews>) reviewsService.listAllReviews(id_property).get();
    }
		
	@GetMapping("/properties/{id_property}/reviews/{id}")
    public ResponseEntity<Reviews> get(@PathVariable Integer id_property, @PathVariable Integer id) {
        try {
            Reviews reviews = reviewsService.getReview(id_property, id).get();
            return new ResponseEntity<Reviews>(reviews,HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Reviews>(HttpStatus.NOT_FOUND);
        }
	}
	
	@PostMapping("/properties/{id_property}/reviews")
	public ResponseEntity<Reviews> add(@PathVariable Integer id_property, @RequestBody Reviews review) throws URISyntaxException {
			
		HttpHeaders header = new HttpHeaders();
		header.setLocation(new URI("/reviews"));
		
		OffsetDateTime current = OffsetDateTime.now();
		String created_datetime = current.toString();	
		review.setCreated_datetime(created_datetime);
		
		review.setIdProperty(id_property);
		Reviews response = reviewsService.saveReview(review);
		return new ResponseEntity<Reviews>(response,header,HttpStatus.CREATED);

    }
	
    @DeleteMapping("/properties/{id_property}/reviews/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id_property, @PathVariable Integer id) {
        String response = reviewsService.deleteReview(id_property,id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
