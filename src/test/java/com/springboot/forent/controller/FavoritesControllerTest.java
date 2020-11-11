package com.springboot.forent.controller;

import static org.mockito.Mockito.doReturn;
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
import org.springframework.http.MediaType;
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
	@DisplayName("GET /favorites WITH RESULT")
	void getFavoritesListHasResult() throws Exception {
		Favorites fave1 = new Favorites(1,1,1);
		Favorites fave2 = new Favorites(2,1,2);
		Favorites fave3 = new Favorites(3,2,3);

		List<Favorites> list = new ArrayList<Favorites>();
		list.add(fave1);
		list.add(fave2);
		list.add(fave3);
		
		doReturn(list).when(service).listAllFavorites();

		mockMvc.perform(get("/favorites"))

			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))

			.andExpect(jsonPath("$.[0].id_favorite").value(1))
			.andExpect(jsonPath("$.[0].id_property").value(1))
			.andExpect(jsonPath("$.[0].id_user").value(1))
			.andExpect(jsonPath("$.[1].id_favorite").value(2))
			.andExpect(jsonPath("$.[1].id_property").value(1))
			.andExpect(jsonPath("$.[1].id_user").value(2))
			.andExpect(jsonPath("$.[2].id_favorite").value(3))
			.andExpect(jsonPath("$.[2].id_property").value(2))
			.andExpect(jsonPath("$.[2].id_user").value(3));
	}
	
	
	@Test
	@DisplayName("GET /favorites WITH NO RESULT")
	void getFavoritesListNoResult() throws Exception {
		doReturn(new ArrayList<Favorites>()).when(service).listAllFavorites();

		mockMvc.perform(get("/favorites"))

			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))

			.andExpect(content().string("[]"));
	}
	
	@Test
	@DisplayName("GET /favorites/3 is FOUND")
	void getFavoriteByIdFound() throws Exception {
		Favorites fave1 = new Favorites(1,1,1);
		Favorites fave2 = new Favorites(2,1,2);
		Favorites fave3 = new Favorites(3,2,3);

		List<Favorites> list = new ArrayList<Favorites>();
		list.add(fave1);
		list.add(fave2);
		list.add(fave3);
	
		doReturn(fave2).when(service).getFavorite(2);

		mockMvc.perform(get("/favorites/{id}",2))

			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))

			.andExpect(jsonPath("$.id_favorite").value(2))
			.andExpect(jsonPath("$.id_property").value(1))
			.andExpect(jsonPath("$.id_user").value(2));
	}
	
	
	@Test
	@DisplayName("POST /favorites is SUCCESSFUL")
	void addFavoriteSuccess() throws Exception {
		Favorites fave = new Favorites(1,1,1);
		doReturn(fave).when(service).saveFavorite(fave);	
		
		mockMvc.perform(post("/favorites")
			.contentType(MediaType.APPLICATION_JSON)
			.content(asJsonString(fave)))
		
			.andExpect(status().isCreated());
			//.andExpect(header().string(HttpHeaders.LOCATION,"/favorites"));
	}
	
	public String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
