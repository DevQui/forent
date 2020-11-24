package com.springboot.forent.service;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.forent.exception.DataNotFoundException;
import com.springboot.forent.exception.NoDataFoundException;
import com.springboot.forent.model.Reviews;
import com.springboot.forent.repository.ReviewsRepository;

@Service
public class ReviewsService {
	@Autowired
	ReviewsRepository reviewsRepository;
	
	public List<Reviews> listAllPropertyReviews(Integer idProperty){
		List<Reviews> reviews = reviewsRepository.findByIdProperty(idProperty);
		if(!reviews.isEmpty()) {
			return reviews;
		}else {
			throw new DataNotFoundException(idProperty);
		}
	}
	
	public Reviews getPropertyReview(Integer id_property, Integer id) {
       Reviews review = reviewsRepository.findByIdReviewIdProprerty(id_property, id);
       if(review != null) {
    	  return review; 
       }else {
    	   throw new DataNotFoundException(id);
       }
   	}
	
	public ResponseEntity<String> saveReview(Integer id_property,Reviews review) {
		OffsetDateTime current = OffsetDateTime.now();
		String created_datetime = current.toString();
		
		Integer saveReviewStatus = reviewsRepository.saveReview(id_property, review.getId_user(),
				review.getRating(), review.getComment(), created_datetime);
		if(saveReviewStatus > 0) {
			return new ResponseEntity<String>("Successfully Added Review", HttpStatus.CREATED);
		}else {
			throw new DataNotFoundException(id_property);
		}
		
   	}   

	public ResponseEntity<String> deleteReview(Integer id_property, Integer id) {
	   Integer deletedReviewStatus = reviewsRepository.deleteReviewFromProperty(id_property, id);
       if(deletedReviewStatus > 0) {
    	   return new ResponseEntity<String>("Successfully Deleted Review", HttpStatus.OK); 
       }else {
    	   throw new DataNotFoundException(id);
       }
	}

	public List<Reviews> listAllReviews() {
		List<Reviews> reviews = (List<Reviews>) reviewsRepository.findAll();
		if(!reviews.isEmpty()) {
			return reviews;
		}else {
			throw new NoDataFoundException();
		}
		
	}

	public Reviews getReview(Integer id_review) {
		try{
			Reviews review = reviewsRepository.findById(id_review).get();
			return review;
		}catch(Exception ex){
			throw new DataNotFoundException(id_review);
		}
		
	}
}
