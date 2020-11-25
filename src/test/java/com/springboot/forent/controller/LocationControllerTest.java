package com.springboot.forent.controller;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.forent.model.Location;
import com.springboot.forent.service.LocationService;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@WebMvcTest(LocationController.class)
class LocationControllerTest {
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private LocationService service;
	
	@Test
	@DisplayName("GET /properites/{id_property}/location WITH RESULT")
	void getLocationListHasResult() throws Exception {
		Location loc1 = new Location(1,1,"Town1","City1","Region1","Country1");
		Location loc2 = new Location(2,2,"Town2","City2","Region2","Country2");
		Location loc3 = new Location(3,3,"Town3","City3","Region3","Country3");

		List<Location> list = new ArrayList<Location>();
		list.add(loc1);
		list.add(loc2);
		list.add(loc3);
		
		doReturn(list).when(service).listAllLocation();

		mockMvc.perform(get("/location"))

			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))

			.andExpect(jsonPath("$.[0].id_location").value(1))
			.andExpect(jsonPath("$.[0].id_property").value(1))
			.andExpect(jsonPath("$.[0].town").value("Town1"))
			.andExpect(jsonPath("$.[0].city").value("City1"))
			.andExpect(jsonPath("$.[0].region").value("Region1"))
			.andExpect(jsonPath("$.[0].country").value("Country1"))
			.andExpect(jsonPath("$.[1].id_location").value(2))
			.andExpect(jsonPath("$.[1].id_property").value(2))
			.andExpect(jsonPath("$.[1].town").value("Town2"))
			.andExpect(jsonPath("$.[1].city").value("City2"))
			.andExpect(jsonPath("$.[1].region").value("Region2"))
			.andExpect(jsonPath("$.[1].country").value("Country2"))
			.andExpect(jsonPath("$.[2].id_location").value(3))
			.andExpect(jsonPath("$.[2].id_property").value(3))
			.andExpect(jsonPath("$.[2].town").value("Town3"))
			.andExpect(jsonPath("$.[2].city").value("City3"))
			.andExpect(jsonPath("$.[2].region").value("Region3"))
			.andExpect(jsonPath("$.[2].country").value("Country3"));
	}
	
	@Test
	@DisplayName("GET /location/{id_location} is FOUND")
	void getLocation() throws Exception {
		Location loc1 = new Location(1,1,"Town1","City1","Region1","Country1");
		Location loc2 = new Location(2,2,"Town2","City2","Region2","Country2");
		Location loc3 = new Location(3,3,"Town3","City3","Region3","Country3");

		List<Location> list = new ArrayList<Location>();
		list.add(loc1);
		list.add(loc2);
		list.add(loc3);
	
		doReturn(loc3).when(service).getLocation(3);

		mockMvc.perform(get("/location/{id_location}",3))

			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))

			.andExpect(jsonPath("$.id_location").value(3))
			.andExpect(jsonPath("$.id_property").value(3))
			.andExpect(jsonPath("$.town").value("Town3"))
			.andExpect(jsonPath("$.city").value("City3"))
			.andExpect(jsonPath("$.region").value("Region3"))
			.andExpect(jsonPath("$.country").value("Country3"));
	}
	
	
	@Test
	@DisplayName("GET /properites/{id_property}/location/{id_location} is FOUND")
	void getPropertyLocation() throws Exception {
		Location loc1 = new Location(1,1,"Town1","City1","Region1","Country1");
		Location loc2 = new Location(2,2,"Town2","City2","Region2","Country2");
		Location loc3 = new Location(3,3,"Town3","City3","Region3","Country3");

		List<Location> list = new ArrayList<Location>();
		list.add(loc1);
		list.add(loc2);
		list.add(loc3);
	
		doReturn(loc3).when(service).getPropertyLocation(3,3);

		mockMvc.perform(get("/properties/{id_property}/location/{id_location}",3,3))

			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))

			.andExpect(jsonPath("$.id_location").value(3))
			.andExpect(jsonPath("$.id_property").value(3))
			.andExpect(jsonPath("$.town").value("Town3"))
			.andExpect(jsonPath("$.city").value("City3"))
			.andExpect(jsonPath("$.region").value("Region3"))
			.andExpect(jsonPath("$.country").value("Country3"));
	}
	
	
	@Test
	@DisplayName("POST /properties/{id_property}/location is SUCCESSFUL")
	void savePropertyLocation() throws Exception {
		Location loc = new Location(1,1,"Town1","City1","Region1","Country1");
		
		ResponseEntity<String> response = new ResponseEntity<String>("Successfully Added Property Location", HttpStatus.CREATED);
		doReturn(response).when(service).savePropertyLocation(1,loc);	
		
		mockMvc.perform(post("/properties/{id_property}/location",1)
			.contentType(MediaType.APPLICATION_JSON)
			.content(asJsonString(loc)))
		
			.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("PATCH /location/1 is SUCCESSFUL")
	void updateUserSuccess() throws Exception{
		
		Location locPut = new Location(1,1,"Town22","City1","Region1","Country1");
		
		ResponseEntity<String> response = new ResponseEntity<String>("Successfully Updated Property Location", HttpStatus.CREATED);
		
		doReturn(response).when(service).updatePropertyLocation(locPut, 1, 1);
		
		mockMvc.perform(patch("/properties/{id_property}/location/{id_location}",1,1)
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(locPut)))
			
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
