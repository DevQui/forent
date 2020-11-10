package com.springboot.forent.controller;


import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
	
	
	@Test
	@DisplayName("GET /users WITH NO RESULT")
	void getUsersListNoResult() throws Exception {
		// Mocked the users and the service
		doReturn(new ArrayList<Users>()).when(service).listAllUsers();

		mockMvc.perform(get("/users"))

			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))

			.andExpect(content().string("[]"));
	}
	
	@Test
	@DisplayName("GET /users/3 is FOUND")
	void getUserByIdFound() throws Exception {
		// Mocked the users and the service
		Users user1 = new Users(1, "host","John", "Middle Name", "Last-Name-John", "john@gmail.com", "+6911111111111", "password123","2020-11-09 11:00:00");
		Users user2 = new Users(2, "tenant","Jane", "Middle Name", "Last-Name-Jane", "jane@gmail.com", "+6911111111112", "password123","2020-11-09 11:00:00");
		Users user3 = new Users(3, "tenant","James", "Middle Name", "Last-Name-James", "james@gmail.com", "+6911111111113", "password123","2020-11-09 11:00:00");

		List<Users> list = new ArrayList<Users>();
		list.add(user1);
		list.add(user2);
		list.add(user3);
	
		doReturn(user3).when(service).getUser(3);

		mockMvc.perform(get("/users/{id}",3))

			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))

			.andExpect(jsonPath("$.id_user").value(3))
			.andExpect(jsonPath("$.type").value("tenant"))
			.andExpect(jsonPath("$.first_name").value("James"));
	}
	
	
	@Test
	@DisplayName("POST /users is SUCCESSFUL")
	void addUserSuccess() throws Exception {
		// Mocked the users and the service
		Users user = new Users(1, "host","John", "Middle Name", "Last-Name-John", "john@gmail.com", "+6911111111111", "password123","2020-11-09 11:00:00");
		doReturn(user).when(service).saveUser(user);	
		
		mockMvc.perform(post("/users")
			.contentType(MediaType.APPLICATION_JSON)
			.content(asJsonString(user)))
		
			.andExpect(status().isCreated())
			.andExpect(header().string(HttpHeaders.LOCATION,"/users"));
	}
	
	@Test
	@DisplayName("PUT /users/1 is SUCCESSFUL")
	void updateUserSuccess() throws Exception{
		Users userFind = new Users(1, "host","John", "Middle Name", "Last-Name-John", "john@gmail.com", "+6911111111111", "password123","2020-11-09 11:00:00");
		Users userPut = new Users(1,"host","John22", "Middle Name", "Last-Name-John", "john@gmail.com", "+6911111111111", "password123","2020-11-09 11:00:00");
		doReturn(userFind).when(service).getUser(1);
		doReturn(userPut).when(service).saveUser(userPut);
		
		mockMvc.perform(put("/users/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(userPut)))
			
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		
				.andExpect(jsonPath("$.id_user").value(1))
				.andExpect(jsonPath("$.type").value("host"))
				.andExpect(jsonPath("$.first_name").value("John22"));
			
	}
	
	public String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
	
	
}