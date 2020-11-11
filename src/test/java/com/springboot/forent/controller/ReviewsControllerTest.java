package com.springboot.forent.controller;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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
import com.springboot.forent.model.Schedules;
import com.springboot.forent.model.Users;
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
	@DisplayName("GET /reviews WITH RESULT")
	void getReviewsListWithResult() throws Exception{
		Reviews review1 = new Reviews(1, 4, "Awesome host", "2020-11-01", 1);
		Reviews review2 = new Reviews(2, 3, "So-so", "2020-11-03", 2);
		Reviews review3 = new Reviews(3, 3, "Good location", "2020-11-01", 3);
		
		List<Reviews> list = new ArrayList<Reviews>();
		list.add(review1);
		list.add(review2);
		list.add(review3);
		
		doReturn(list).when(service).listAllReviews();

		mockMvc.perform(get("/reviews"))

			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))

			.andExpect(jsonPath("$.[0].id_review").value(1))
			.andExpect(jsonPath("$.[0].rating").value(4))
			.andExpect(jsonPath("$.[0].comment").value("Awesome host"))
			.andExpect(jsonPath("$.[0].created_datetime").value("2020-11-01"))
			.andExpect(jsonPath("$.[0].id_property").value(1))
			.andExpect(jsonPath("$.[1].id_review").value(2))
			.andExpect(jsonPath("$.[1].rating").value(3))
			.andExpect(jsonPath("$.[1].comment").value("So-so"))
			.andExpect(jsonPath("$.[1].created_datetime").value("2020-11-03"))
			.andExpect(jsonPath("$.[1].id_property").value(2))
			.andExpect(jsonPath("$.[2].id_review").value(3))
			.andExpect(jsonPath("$.[2].rating").value(3))
			.andExpect(jsonPath("$.[2].comment").value("Good location"))
			.andExpect(jsonPath("$.[2].created_datetime").value("2020-11-01"))
			.andExpect(jsonPath("$.[2].id_property").value(3));
	}
	
	@Test
	@DisplayName("GET /reviews WITH NO RESULT")
	void getReviewsListNoResult() throws Exception {
		doReturn(new ArrayList<Reviews>()).when(service).listAllReviews();

		mockMvc.perform(get("/reviews"))

			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))

			.andExpect(content().string("[]"));
	}
	
	@Test
	@DisplayName("GET /reviews/3 is FOUND")
	void getReviewsByIdFound() throws Exception {
		Reviews review1 = new Reviews(1, 4, "Awesome host", "2020-11-01", 1);
		Reviews review2 = new Reviews(2, 3, "So-so", "2020-11-03", 2);
		Reviews review3 = new Reviews(3, 3, "Good location", "2020-11-01", 3);
		
		List<Reviews> list = new ArrayList<Reviews>();
		list.add(review1);
		list.add(review2);
		list.add(review3);
	
		doReturn(review3).when(service).getReview(3);

		mockMvc.perform(get("/reviews/{id}",3))

			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))

			.andExpect(jsonPath("$.id_review").value(3))
			.andExpect(jsonPath("$.rating").value(3))
			.andExpect(jsonPath("$.comment").value("Good location"))
			.andExpect(jsonPath("$.created_datetime").value("2020-11-01"))
			.andExpect(jsonPath("$.id_property").value(3));
	}
	
	
	@Test
	@DisplayName("POST /reviews is SUCCESSFUL")
	void addReviewsSuccess() throws Exception {
		// Mocked the users and the service
		Reviews review1 = new Reviews(1, 4, "Awesome host", "2020-11-01", 1);
		doReturn(review1).when(service).saveReview(review1);	
		
		mockMvc.perform(post("/reviews")
			.contentType(MediaType.APPLICATION_JSON)
			.content(asJsonString(review1)))
		
			.andExpect(status().isCreated())
			.andExpect(header().string(HttpHeaders.LOCATION,"/reviews"));
	}
	
	public String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
