package com.springboot.forent.service;

import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.forent.model.Reviews;
import com.springboot.forent.repository.ReviewsRepository;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@WebMvcTest(ReviewsService.class)
class ReviewsServiceTest {
	@Autowired
	ReviewsService service;
	
	@MockBean
	ReviewsRepository repo;
	
	@Test
	@DisplayName("TEST getReviewsHasResult")
	void getReviewsHasResult() throws Exception {
		Reviews review1 = new Reviews(1, 4, "Awesome host", "2020-11-01", 1);
		Reviews review2 = new Reviews(2, 3, "So-so", "2020-11-03", 2);
		Reviews review3 = new Reviews(3, 3, "Good location", "2020-11-01", 3);
		
		List<Reviews> list = new ArrayList<Reviews>();
		list.add(review1);
		list.add(review2);
		list.add(review3);
		
		doReturn(list).when(repo).findAll();
		// Call service
		List<Reviews> returnedList = (List<Reviews>) service.listAllReviews();
		// Validate
		Assertions.assertFalse(returnedList.isEmpty(), "No result.");
		Assertions.assertSame(returnedList.get(0), review1, "User should be the same.");
		Assertions.assertEquals(returnedList.get(2).getRating(), 3);
	}
	
	@Test
	@DisplayName("TEST getReviewsByID")
	void getReviewsByID() throws Exception{
		Reviews review1 = new Reviews(1, 4, "Awesome host", "2020-11-01", 1);
		doReturn(Optional.of(review1)).when(repo).findById(1);
		
		Reviews review = service.getReview(1);
		
		Assertions.assertEquals(review.getId_review(),1);
		Assertions.assertEquals(review.getRating(),4);
		Assertions.assertEquals(review.getComment(),"Awesome host");
		Assertions.assertEquals(review.getCreated_datetime(),"2020-11-01");
		Assertions.assertEquals(review.getId_property(), 1);
	}
	
	@Test
	@DisplayName("TEST saveReview")
	void saveReview() throws Exception{
		Reviews review = new Reviews(3, 3, "Good location", "2020-11-01", 3);
		doReturn(review).when(repo).save(review);
		
		Reviews addedReview = service.saveReview(review);
		
		Assertions.assertEquals(addedReview.getId_review(),3);
		Assertions.assertEquals(addedReview.getRating(),3);
		Assertions.assertEquals(addedReview.getComment(),"Good location");
		Assertions.assertEquals(addedReview.getCreated_datetime(),"2020-11-01");
		Assertions.assertEquals(addedReview.getId_property(), 3);	
	}
	
	@Test
	@DisplayName("TEST deleteReview")
	void deleteReview() throws Exception{
		Reviews review1 = new Reviews(1, 4, "Awesome host", "2020-11-01", 1);
				
		repo.delete(review1);
		String response = service.deleteReview(1);
		
		Assertions.assertEquals(response, "Review Deleted");
	}
}
