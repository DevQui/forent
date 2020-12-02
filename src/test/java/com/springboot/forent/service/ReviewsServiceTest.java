package com.springboot.forent.service;

import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.forent.exception.DataNotFoundException;
import com.springboot.forent.exception.NoDataFoundException;
import com.springboot.forent.model.Reviews;
import com.springboot.forent.repository.ReviewsRepository;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@WebMvcTest(ReviewsService.class)
class ReviewsServiceTest {
	/*@Autowired
	ReviewsService service;
	
	@MockBean
	ReviewsRepository repo;
	
	@Test
	@DisplayName("TEST listAllReviews")
	void listAllReviews() throws Exception {
		Reviews review1 = new Reviews(1, 1, 4, "Awesome host", "2020-11-01");
		Reviews review2 = new Reviews(2, 2, 3, "So-so", "2020-11-03");
		Reviews review3 = new Reviews(3, 3, 3, "Good location", "2020-11-01");
		
		List<Reviews> list = new ArrayList<Reviews>();
		list.add(review1);
		list.add(review2);
		list.add(review3);
		
		doReturn(list).when(repo).findAll();

		List<Reviews> returnedList = (List<Reviews>) service.listAllReviews();

		Assertions.assertFalse(returnedList.isEmpty(), "No result.");
		Assertions.assertSame(returnedList.get(0), review1, "User should be the same.");
		Assertions.assertEquals(returnedList.get(2).getRating(), 3);
	}
	
	@Test
	@DisplayName("TEST getReview")
	void getReview() throws Exception {
		Reviews review1 = new Reviews(1, 1, 4, "Awesome host", "2020-11-01");
		Reviews review2 = new Reviews(2, 2, 3, "So-so", "2020-11-03");
		Reviews review3 = new Reviews(3, 3, 3, "Good location", "2020-11-01");
		
		List<Reviews> list = new ArrayList<Reviews>();
		list.add(review1);
		list.add(review2);
		list.add(review3);
		
		doReturn(Optional.of(review1)).when(repo).findById(review1.getIdReview());

		Reviews review = service.getReview(review1.getIdReview());

		Assertions.assertEquals(review.getId_user(), 1);
		Assertions.assertEquals(review.getRating(), 4);
		Assertions.assertEquals(review.getComment(), "Awesome host");
	}
	
	@Test
	@DisplayName("TEST listAllPropertyReviews")
	void listAllPropertyReviews() throws Exception {
		Reviews review1 = new Reviews(1, 1, 4, "Awesome host", "2020-11-01");
		Reviews review2 = new Reviews(2, 2, 3, "So-so", "2020-11-03");
		Reviews review3 = new Reviews(3, 3, 3, "Good location", "2020-11-01");
		
		List<Reviews> list = new ArrayList<Reviews>();
		list.add(review1);
		list.add(review2);
		list.add(review3);
		
		doReturn(list).when(repo).findByIdProperty(3);

		List<Reviews> returnedList = (List<Reviews>) service.listAllPropertyReviews(3);

		Assertions.assertFalse(returnedList.isEmpty(), "No result.");
		Assertions.assertSame(returnedList.get(0), review1, "User should be the same.");
		Assertions.assertEquals(returnedList.get(2).getRating(), 3);
	}
	
	@Test
	@DisplayName("TEST getPropertyReview")
	void getPropertyReview() throws Exception{
		Reviews review1 = new Reviews(1, 1, 1, 4, "Awesome host", "2020-11-01");
		doReturn(review1).when(repo).findByIdReviewIdProprerty(1,1);
		
		Reviews review = service.getPropertyReview(1,1);
		
		Assertions.assertEquals(review.getIdReview(), 1);
		Assertions.assertEquals(review.getIdProperty(), 1);
		Assertions.assertEquals(review.getId_user(),1);
		Assertions.assertEquals(review.getRating(),4);
		Assertions.assertEquals(review.getComment(),"Awesome host");
		Assertions.assertEquals(review.getCreated_datetime(),"2020-11-01");
	}
	
	@Test
	@DisplayName("TEST saveReview")
	void saveReview() throws Exception{
		Reviews review = new Reviews(3, 1, 3, "Good location", "2020-11-01");
		
		Integer id_property = 1;
		Integer savedReviewStatus = 1;
		
		doReturn(savedReviewStatus).when(repo).saveReview(Mockito.anyInt(), Mockito.anyInt(), 
				Mockito.anyInt(), Mockito.anyString(), Mockito.anyString());
		
		ResponseEntity<String> response = service.saveReview(id_property, review);
		
		Assertions.assertEquals(response.getStatusCodeValue(), 201);			
	}
	
	@Test
	@DisplayName("TEST deleteReview")
	void deleteReview() throws Exception{
		Reviews review1 = new Reviews(1, 1, 4, "Awesome host", "2020-11-01");
		Integer deleteReviewStatus = 1;
		
		doReturn(deleteReviewStatus).when(repo).deleteReviewFromProperty(review1.getIdProperty(), review1.getIdReview());
		
		ResponseEntity<String> response = service.deleteReview(review1.getIdProperty(), review1.getIdReview());
		
		Assertions.assertEquals(response.getStatusCodeValue(), 200);
	}
	
	@Test
	@DisplayName("TEST listAllReviewsEMPTY")
	void listAllReviewsEMPTY() throws NoDataFoundException {
		Assertions.assertThrows(NoDataFoundException.class, () -> {
			service.listAllReviews();
		  });
	}
	
	@Test
	@DisplayName("TEST getReviewNOTFOUND")
	void getReviewNOTFOUND() throws DataNotFoundException {
		Assertions.assertThrows(DataNotFoundException.class, () -> {
			service.getReview(1);
		  });
	}
	
	@Test
	@DisplayName("TEST listAllReviewsNOTFOUND")
	void listAllPropertyReviewsNOTFOUND() throws DataNotFoundException {
		Assertions.assertThrows(DataNotFoundException.class, () -> {
			service.listAllPropertyReviews(1);
		  });
	}
	
	@Test
	@DisplayName("TEST getPropertyReviewNOTFOUND")
	void getPropertyReviewNOTFOUND() throws DataNotFoundException {
		Assertions.assertThrows(DataNotFoundException.class, () -> {
			service.getPropertyReview(1,1);
		  });
	}
	
	@Test
	@DisplayName("TEST saveReviewNOTFOUND")
	void saveReviewNOTFOUND() throws DataNotFoundException {
		Assertions.assertThrows(DataNotFoundException.class, () -> {
			service.saveReview(1,new Reviews());
		  });
	}
	
	@Test
	@DisplayName("TEST deleteReviewNOTFOUND")
	void deleteReviewNOTFOUND() throws DataNotFoundException {
		Assertions.assertThrows(DataNotFoundException.class, () -> {
			service.deleteReview(1,1);
		  });
	}*/
}
