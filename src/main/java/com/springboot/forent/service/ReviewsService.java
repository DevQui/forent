package com.springboot.forent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.forent.model.Reviews;
import com.springboot.forent.repository.ReviewsRepository;

@Service
public class ReviewsService {
	@Autowired
	ReviewsRepository reviewsRepository;
	
	public List<Reviews> listAllReviews(){
		 return (List<Reviews>) reviewsRepository.findAll();
	}
	
	public Reviews saveReview(Reviews review) {
		return reviewsRepository.save(review);
   }

   public Reviews getReview(Integer id) {
       return reviewsRepository.findById(id).get();
   }

   public String deleteReview(Integer id) {
	   reviewsRepository.deleteById(id);
       return "Review Deleted";
   }
}
