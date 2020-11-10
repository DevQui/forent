package com.springboot.forent.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.springboot.forent.model.Users;
import com.springboot.forent.service.UsersService;

class UsersControllerTest {
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private UsersService service;
	
	@Test
	@DisplayName("GET /users WITH RESULT")
	void getUsersListHasResult() throws Exception {
		// Mocked the users and the service
		Users user1 = new Users(1, "host","John", "Middle Name", "Manager", "john@gmail.com", "+6911111111111", "password123","2020-11-09 11:00:00");
		Users user2 = new Users(2, "tenant","Jane", "Middle Name", "Director", "jane@gmail.com", "+6911111111112", "password123","2020-11-09 11:00:00");
		Users user3 = new Users(3, "tenant","James", "Middle Name", "Supervisor", "james@gmail.com", "+6911111111113", "password123","2020-11-09 11:00:00");
		List<Users> list = new ArrayList<Users>();
		list.add(user1);
		list.add(user2);
		list.add(user3);
		doReturn(list).when(service).getEmployees();
		// Execute the request
		mockMvc.perform(get("/users"))
			// Validate the response code and content type
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			// Validate the response body
			.andExpect(jsonPath("$.[0].id_user", is(1)))
			.andExpect(jsonPath("$.[0].first_name", is("John")))
			.andExpect(jsonPath("$.[0].last_name", is("Manager")));
	}
)