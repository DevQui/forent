package com.springboot.forent.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.forent.model.Reviews;
import com.springboot.forent.repository.ReviewsRepository;

@Service
public class ReviewsService {
	@Autowired
	ReviewsRepository reviewsRepository;
	
	public Optional<Reviews> listAllReviews(Integer idProperty){
		return reviewsRepository.findByIdProperty(idProperty);
	}
	
	public Reviews saveReview(Reviews review) {
		Reviews reviews = reviewsRepository.save(review); 
		return reviews;
   	}

	public Optional<Reviews> getReview(Integer id_property, Integer id) {
       return reviewsRepository.findByIdReviewIdProprerty(id_property, id);
   	}
   

	public String deleteReview(Integer id_property, Integer id) {
	   reviewsRepository.deleteReviewFromProperty(id_property, id);
       return "Review Deleted";
	}
}
