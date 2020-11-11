package com.springboot.forent.controller;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
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
import com.springboot.forent.model.Amenities;
import com.springboot.forent.model.Users;
import com.springboot.forent.service.AmenitiesService;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@WebMvcTest(AmenitiesController.class)
class AmenitiesControllerTest {

	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private AmenitiesService service;
	
	@Test
	@DisplayName("GET /amenities WITH RESULT")
	void getAmenitiesListHasResult() throws Exception {
		Amenities amenity1 = new Amenities(1, 1, 2, 1, 2, "Wifi, Cable");
		Amenities amenity2 = new Amenities(2, 2, 2, 2, 1, "Gym, Pool");
		Amenities amenity3 = new Amenities(3, 3, 1, 1, 1, "Microwave, Ref");

		List<Amenities> list = new ArrayList<Amenities>();
		list.add(amenity1);
		list.add(amenity2);
		list.add(amenity3);
		
		doReturn(list).when(service).listAllAmenities();

		mockMvc.perform(get("/amenities"))

			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))

			.andExpect(jsonPath("$.[0].id_amenity").value(1))
			.andExpect(jsonPath("$.[0].id_property").value(1))
			.andExpect(jsonPath("$.[0].rooms").value(2))
			.andExpect(jsonPath("$.[0].toilets").value(1))
			.andExpect(jsonPath("$.[0].beds").value(2))
			.andExpect(jsonPath("$.[0].other_amenities").value("Wifi, Cable"))
			.andExpect(jsonPath("$.[1].id_amenity").value(2))
			.andExpect(jsonPath("$.[1].id_property").value(2))
			.andExpect(jsonPath("$.[1].rooms").value(2))
			.andExpect(jsonPath("$.[1].toilets").value(2))
			.andExpect(jsonPath("$.[1].beds").value(1))
			.andExpect(jsonPath("$.[1].other_amenities").value("Gym, Pool"))
			.andExpect(jsonPath("$.[2].id_amenity").value(3))
			.andExpect(jsonPath("$.[2].id_property").value(3))
			.andExpect(jsonPath("$.[2].rooms").value(1))
			.andExpect(jsonPath("$.[2].toilets").value(1))
			.andExpect(jsonPath("$.[2].beds").value(1))
			.andExpect(jsonPath("$.[2].other_amenities").value("Microwave, Ref"));
	}
	
	
	@Test
	@DisplayName("GET /amenities WITH NO RESULT")
	void getAmenitiesListNoResult() throws Exception {
		// Mocked the users and the service
		doReturn(new ArrayList<Users>()).when(service).listAllAmenities();

		mockMvc.perform(get("/amenities"))

			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))

			.andExpect(content().string("[]"));
	}
	
	@Test
	@DisplayName("GET /amenities/3 is FOUND")
	void getUserByIdFound() throws Exception {
		Amenities amenity1 = new Amenities(1, 1, 2, 1, 2, "Wifi, Cable");
		Amenities amenity2 = new Amenities(2, 2, 2, 2, 1, "Gym, Pool");
		Amenities amenity3 = new Amenities(3, 3, 1, 1, 1, "Microwave, Ref");

		List<Amenities> list = new ArrayList<Amenities>();
		list.add(amenity1);
		list.add(amenity2);
		list.add(amenity3);
	
		doReturn(amenity3).when(service).getAmenities(3);

		mockMvc.perform(get("/amenities/{id}",3))

			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))

			.andExpect(jsonPath("$.id_amenity").value(3))
			.andExpect(jsonPath("$.id_property").value(3))
			.andExpect(jsonPath("$.rooms").value(1))
			.andExpect(jsonPath("$.toilets").value(1))
			.andExpect(jsonPath("$.beds").value(1))
			.andExpect(jsonPath("$.other_amenities").value("Microwave, Ref"));
	}
	
	
	@Test
	@DisplayName("POST /amenities is SUCCESSFUL")
	void addAmenitiesSuccess() throws Exception {
		// Mocked the users and the service
		Amenities amenity = new Amenities(1, 1, 2, 1, 2, "Wifi, Cable");
		doReturn(amenity).when(service).saveAmenities(amenity);	
		
		mockMvc.perform(post("/amenities")
			.contentType(MediaType.APPLICATION_JSON)
			.content(asJsonString(amenity)))
		
			.andExpect(status().isCreated());
			//.andExpect(header().string(HttpHeaders.LOCATION,"/users"));
	}
	
	@Test
	@DisplayName("PATCH /amenities/1 is SUCCESSFUL")
	void updateAmenitiesSuccess() throws Exception{
		Amenities amenityFind = new Amenities(1, 1, 2, 1, 2, "Wifi, Cable");
		Amenities amenityPut = new Amenities(1, 1, 3, 2, 1, "Wifi, Cable");
		doReturn(amenityFind).when(service).getAmenities(1);
		doReturn(amenityPut).when(service).saveAmenities(amenityPut);
		
		mockMvc.perform(patch("/amenities/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(amenityPut)))
			
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		
				.andExpect(jsonPath("$.id_amenity").value(1))
				.andExpect(jsonPath("$.id_property").value(1))
				.andExpect(jsonPath("$.rooms").value(3))
				.andExpect(jsonPath("$.toilets").value(2))
				.andExpect(jsonPath("$.beds").value(1))
				.andExpect(jsonPath("$.other_amenities").value("Wifi, Cable"));
			
	}
	
	public String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }	

}
