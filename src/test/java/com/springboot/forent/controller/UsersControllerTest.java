package com.springboot.forent.controller;


import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.springboot.forent.model.Users;
import com.springboot.forent.service.UsersService;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@WebMvcTest(UsersController.class)
class UsersControllerTest {
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private UsersService service;
	
	@Test
	@DisplayName("GET /users WITH RESULT")
	void getUsersListHasResult() throws Exception {
		// Mocked the users and the service
		Users user1 = new Users(1, "host","John", "Middle Name", "Last-Name-John", "john@gmail.com", "+6911111111111", "password123","2020-11-09 11:00:00");
		Users user2 = new Users(2, "tenant","Jane", "Middle Name", "Last-Name-Jane", "jane@gmail.com", "+6911111111112", "password123","2020-11-09 11:00:00");
		Users user3 = new Users(3, "tenant","James", "Middle Name", "Last-Name-James", "james@gmail.com", "+6911111111113", "password123","2020-11-09 11:00:00");

		List<Users> list = new ArrayList<Users>();
		list.add(user1);
		list.add(user2);
		list.add(user3);
		
		doReturn(list).when(service).listAllUsers();

		mockMvc.perform(get("/users"))

			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))

			.andExpect(jsonPath("$.[0].id_user").value(1))
			.andExpect(jsonPath("$.[0].type").value("host"))
			.andExpect(jsonPath("$.[0].first_name").value("John"))
			.andExpect(jsonPath("$.[1].id_user").value(2))
			.andExpect(jsonPath("$.[1].type").value("tenant"))
			.andExpect(jsonPath("$.[1].first_name").value("Jane"))
			.andExpect(jsonPath("$.[2].id_user").value(3))
			.andExpect(jsonPath("$.[2].type").value("tenant"))
			.andExpect(jsonPath("$.[2].first_name").value("James"));
	}
}