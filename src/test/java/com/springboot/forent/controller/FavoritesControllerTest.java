package com.springboot.forent.controller;

import static org.mockito.Mockito.doReturn;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.forent.model.Favorites;
import com.springboot.forent.service.FavoritesService;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@WebMvcTest(FavoritesController.class)
class FavoritesControllerTest {
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private FavoritesService service;
	
	@Test
	@DisplayName("GET /users/{id_user}/favorites WITH RESULT")
	@WithMockUser(roles = "tenant")
	void listAllUserFavorites() throws Exception {
		Favorites fave1 = new Favorites(1,1,1);
		Favorites fave2 = new Favorites(2,2,1);
		Favorites fave3 = new Favorites(3,2,3);

		List<Favorites> list = new ArrayList<Favorites>();
		list.add(fave1);
		list.add(fave2);
		list.add(fave3);
		
		doReturn(list).when(service).listAllUserFavorites(1);

		mockMvc.perform(get("/users/{id_user}/favorites",1))

			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))

			.andExpect(jsonPath("$.[0].id_favorite").value(1))
			.andExpect(jsonPath("$.[0].id_property").value(1))
			.andExpect(jsonPath("$.[0].id_user").value(1))
			.andExpect(jsonPath("$.[1].id_favorite").value(2))
			.andExpect(jsonPath("$.[1].id_property").value(2))
			.andExpect(jsonPath("$.[1].id_user").value(1));
	}
		
	@Test
	@DisplayName("GET /users/{id_user}/favorites/{id} is FOUND")
	@WithMockUser(roles = "tenant")
	void getUserFavoriteProperty() throws Exception {
		Favorites fave1 = new Favorites(1,1,1);
		Favorites fave2 = new Favorites(2,2,1);
		Favorites fave3 = new Favorites(3,2,3);

		List<Favorites> list = new ArrayList<Favorites>();
		list.add(fave1);
		list.add(fave2);
		list.add(fave3);
	
		doReturn(fave2).when(service).getUserFavoriteProperty(1,2);

		mockMvc.perform(get("/users/{id_user}/favorites/{id}",1,2))

			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))

			.andExpect(jsonPath("$.id_favorite").value(2))
			.andExpect(jsonPath("$.id_property").value(2))
			.andExpect(jsonPath("$.id_user").value(1));
	}

	
	@Test
	@DisplayName("POST /favorites is SUCCESSFUL")
	@WithMockUser(roles = "tenant")
	void savePropertyToFavorites() throws Exception {
		Favorites fave = new Favorites(1,1,1);
		
		ResponseEntity<String> response = new ResponseEntity<String>("Successfully Added Property to Favorites", HttpStatus.CREATED);
		
		doReturn(response).when(service).savePropertyToFavorites(1,1);	
		
		mockMvc.perform(post("/users/{id_user}/properties/{id_property}/favorites",1,1)
			.with(csrf())
			.contentType(MediaType.APPLICATION_JSON)
			.content(asJsonString(fave)))
		
			.andExpect(status().isCreated());
	}
	
	@Test
	@DisplayName("DELETE /favorites/1 SUCCESS")
	@WithMockUser(roles = "tenant")
	void deleteFavoriteProperty() throws Exception{
		Favorites fave = new Favorites(1,1,1);
		
		ResponseEntity<String> response = new ResponseEntity<String>("Successfully Deleted Property to Favorites", HttpStatus.OK);
		
		doReturn(response).when(service).deleteFavoriteProperty(1,1);
		
		mockMvc.perform(delete("/users/{id_user}/favorites/{id}",1,1)
				.with(csrf())
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(fave)))
			
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
