package com.springboot.forent.controller;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.forent.model.Reviews;
import com.springboot.forent.service.ReviewsService;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@WebMvcTest(ReviewsController.class)
class ReviewsControllerTest {
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private ReviewsService service;
	
	@Test
	@DisplayName("GET properties/{id_property}/reviews WITH RESULT")
	void getReviewsListWithResult() throws Exception{
		Reviews review1 = new Reviews(1, 1, 1, 4, "Awesome host", "2020-11-01");
		Reviews review2 = new Reviews(2, 1, 2, 3, "So-so", "2020-11-03");
		Reviews review3 = new Reviews(3, 1, 3, 3, "Good location", "2020-11-01");
		
		List<Reviews> list = new ArrayList<Reviews>();
		list.add(review1);
		list.add(review2);
		list.add(review3);
		
		doReturn(Optional.of(list)).when(service).listAllReviews(1);

		mockMvc.perform(get("/properties/{id_property}/reviews", 1))

			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			
			.andExpect(jsonPath("$.[0].idReview").value(1))
			.andExpect(jsonPath("$.[0].idProperty").value(1))
			.andExpect(jsonPath("$.[0].id_user").value(1))
			.andExpect(jsonPath("$.[0].rating").value(4))
			.andExpect(jsonPath("$.[0].comment").value("Awesome host"))
			.andExpect(jsonPath("$.[0].created_datetime").value("2020-11-01"))
			.andExpect(jsonPath("$.[1].idReview").value(2))
			.andExpect(jsonPath("$.[1].idProperty").value(1))
			.andExpect(jsonPath("$.[1].id_user").value(2))
			.andExpect(jsonPath("$.[1].rating").value(3))
			.andExpect(jsonPath("$.[1].comment").value("So-so"))
			.andExpect(jsonPath("$.[1].created_datetime").value("2020-11-03"))
			.andExpect(jsonPath("$.[2].idReview").value(3))
			.andExpect(jsonPath("$.[2].idProperty").value(1))
			.andExpect(jsonPath("$.[2].id_user").value(3))
			.andExpect(jsonPath("$.[2].rating").value(3))
			.andExpect(jsonPath("$.[2].comment").value("Good location"))
			.andExpect(jsonPath("$.[2].created_datetime").value("2020-11-01"));
			
	}
	
	@Test
	@DisplayName("GET /properties/{id_proeprty}/reviews WITH NO RESULT")
	void getReviewsListNoResult() throws Exception {
		doReturn(Optional.of(new ArrayList<Reviews>())).when(service).listAllReviews(1);

		mockMvc.perform(get("/properties/{id_property}/reviews",1))

			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))

			.andExpect(content().string("[]"));
	}
	
	@Test
	@DisplayName("GET /properties/{id_properties}/reviews/{id} is FOUND")
	void getReviewsByIdFound() throws Exception {
		Reviews review1 = new Reviews(1, 1, 1, 4, "Awesome host", "2020-11-01");
		Reviews review2 = new Reviews(2, 1, 2, 3, "So-so", "2020-11-03");
		Reviews review3 = new Reviews(3, 1, 3, 3, "Good location", "2020-11-01");
		
		List<Reviews> list = new ArrayList<Reviews>();
		list.add(review1);
		list.add(review2);
		list.add(review3);
	
		doReturn(Optional.of(review3)).when(service).getReview(1,3);

		mockMvc.perform(get("/properties/{id_property}/reviews/{id}",1,3))

			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			
			.andExpect(jsonPath("$.idReview").value(3))
			.andExpect(jsonPath("$.idProperty").value(1))
			.andExpect(jsonPath("$.id_user").value(3))
			.andExpect(jsonPath("$.rating").value(3))
			.andExpect(jsonPath("$.comment").value("Good location"))
			.andExpect(jsonPath("$.created_datetime").value("2020-11-01"));
	}
	
	
	@Test
	@DisplayName("POST /properties/{id_property}/reviews is SUCCESSFUL")
	void addReviewsSuccess() throws Exception {
		// Mocked the users and the service
		Reviews review1 = new Reviews(1, 1, 4, "Awesome host", "2020-11-01");
		doReturn(review1).when(service).saveReview(review1);	
		
		mockMvc.perform(post("/properties/{id_property}/reviews",1)
			.contentType(MediaType.APPLICATION_JSON)
			.content(asJsonString(review1)))
		
			.andExpect(status().isCreated())
			.andExpect(header().string(HttpHeaders.LOCATION,"/reviews"));
	}
	
	@Test
	@DisplayName("DELETE /properties/{id_property}/reviews/1 SUCCESS")
	void deleteReviews() throws Exception{
		Reviews review = new Reviews(1, 1, 4, "Awesome host", "2020-11-01");
		doReturn("Review Deleted").when(service).deleteReview(1,1);
		
		mockMvc.perform(delete("/properties/{id_property}/reviews/{id}",1,1)
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(review)))
			
				.andExpect(status().isOk());
	}
	
	public String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
