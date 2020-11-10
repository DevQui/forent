package com.springboot.forent;

import static org.junit.Assert.assertEquals;

import java.time.OffsetDateTime;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.springboot.forent.service.ReviewsService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ForentApplication.class)
@AutoConfigureMockMvc
class ReviewsTest {	
	@Autowired
	protected MockMvc mvc;
	
	@Autowired
	ReviewsService reviews;

	@Test
	void test() {
		//fail("Not yet implemented");
	}
	
	@Test
	@Rollback(false)
	public void testPostReview() throws Exception {
		/*OffsetDateTime current = OffsetDateTime.now();
		String created_datetime = current.toString();
		Reviews review = new Reviews(3, "A so-so place.", created_datetime, 3);
		
		reviews.saveReview(review);
	    assertEquals(review.getId_property(),3);*/
	}
	
	@Test
	public void testGetReview() throws Exception{
		/*Reviews review = reviews.getReview(3);
		assertEquals(review.getRating(), 3);*/
	}
	

	@Test
	public void testDeleteReview() throws Exception{
	    /*String uri = "http://localhost:8080/reviews/6";
	    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
	    int status = mvcResult.getResponse().getStatus();
	    assertEquals(200, status);*/
	}

}
