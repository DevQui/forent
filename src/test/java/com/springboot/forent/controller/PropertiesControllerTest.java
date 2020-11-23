package com.springboot.forent.controller;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import com.springboot.forent.model.Amenities;
import com.springboot.forent.model.Location;
import com.springboot.forent.model.Properties;
import com.springboot.forent.model.Reviews;
import com.springboot.forent.model.UserProperties;
import com.springboot.forent.service.PropertiesService;


@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@WebMvcTest(PropertiesController.class)
class PropertiesControllerTest {

	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private PropertiesService service;
	
	@Test
	@DisplayName("GET /properties WITH RESULT")
	void getPropertiesListHasResult() throws Exception {
		// Mocked the users and the service
		Properties property1 = new Properties(1, "bungalow", "Bungalow Property", "A Bungalow Property", (float)3999.00, "2020-11-01 11:00:00");
		Properties property2 = new Properties(2, "apartment", "Apartment Property", "An Apartment Property", (float)1999.00, "2020-11-01 11:00:00");
		Properties property3 = new Properties(3, "condominium", "Condominium Property", "A Condominium Property", (float)2999.00, "2020-11-01 11:00:00");

		List<Properties> list = new ArrayList<Properties>();
		list.add(property1);
		list.add(property2);
		list.add(property3);
		
		doReturn(list).when(service).listAllProperties();

		mockMvc.perform(get("/properties"))

			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))

			.andExpect(jsonPath("$.[0].id_property").value(1))
			.andExpect(jsonPath("$.[0].type").value("bungalow"))
			.andExpect(jsonPath("$.[0].name").value("Bungalow Property"))
			.andExpect(jsonPath("$.[0].description").value("A Bungalow Property"))
			.andExpect(jsonPath("$.[0].price").value(3999.00))
			.andExpect(jsonPath("$.[0].updated_datetime").value("2020-11-01 11:00:00"))
			.andExpect(jsonPath("$.[1].id_property").value(2))
			.andExpect(jsonPath("$.[1].type").value("apartment"))
			.andExpect(jsonPath("$.[1].name").value("Apartment Property"))
			.andExpect(jsonPath("$.[1].description").value("An Apartment Property"))
			.andExpect(jsonPath("$.[1].price").value(1999.00))
			.andExpect(jsonPath("$.[0].updated_datetime").value("2020-11-01 11:00:00"))
			.andExpect(jsonPath("$.[2].id_property").value(3))
			.andExpect(jsonPath("$.[2].type").value("condominium"))
			.andExpect(jsonPath("$.[2].name").value("Condominium Property"))
			.andExpect(jsonPath("$.[2].description").value("A Condominium Property"))
			.andExpect(jsonPath("$.[2].price").value(2999.00))
			.andExpect(jsonPath("$.[2].updated_datetime").value("2020-11-01 11:00:00"));
	}
	
	
	@Test
	@DisplayName("GET /properties WITH NO RESULT")
	void getPropertiesListNoResult() throws Exception {
		// Mocked the users and the service
		doReturn(new ArrayList<Properties>()).when(service).listAllProperties();

		mockMvc.perform(get("/properties"))

			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))

			.andExpect(content().string("[]"));
	}
	
	@Test
	@DisplayName("GET /proerties/{id_properties} is FOUND")
	void getPropertyByIdFound() throws Exception {
		// Mocked the users and the service
		Properties property1 = new Properties(1, "bungalow", "Bungalow Property", "A Bungalow Property", (float)3999.00, "2020-11-01 11:00:00");
		Properties property2 = new Properties(2, "apartment", "Apartment Property", "An Apartment Property", (float)1999.00, "2020-11-01 11:00:00");
		Properties property3 = new Properties(3, "condominium", "Condominium Property", "A Condominium Property", (float)2999.00, "2020-11-01 11:00:00");

		List<Properties> list = new ArrayList<Properties>();
		list.add(property1);
		list.add(property2);
		list.add(property3);
		
		ResponseEntity<Properties> response = new ResponseEntity<Properties>(property3, HttpStatus.OK);
	
		doReturn(response).when(service).getProperty(3);

		mockMvc.perform(get("/properties/{id}",3))

			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))

			.andExpect(jsonPath("$.id_property").value(3))
			.andExpect(jsonPath("$.type").value("condominium"))
			.andExpect(jsonPath("$.name").value("Condominium Property"))
			.andExpect(jsonPath("$.description").value("A Condominium Property"))
			.andExpect(jsonPath("$.price").value(2999.00))
			.andExpect(jsonPath("$.updated_datetime").value("2020-11-01 11:00:00"));
	}
	
	@Test
	@DisplayName("POST /users/{id_user}/properties SUCCESS")
	void addPropertySuccess() throws Exception {
		Properties property = new Properties(1, "bungalow", "Bungalow Property", "A Bungalow Property", (float)3999.00, "2020-11-01 11:00:00");
		
		ResponseEntity<Properties> response = new ResponseEntity<Properties>(property,HttpStatus.CREATED);
		
		doReturn(response).when(service).addProperty(1, property);
		
		mockMvc.perform(post("/users/{id_user}/properties",1)
			.contentType(MediaType.APPLICATION_JSON)
			.content(asJsonString(property)))
		
			.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("GET /users/{id_users}/proerties is FOUND")
	void getPropertiesOfUser() throws Exception {
		UserProperties user1 = new UserProperties(1, "host","John", "Middle Name", "Last-Name-John", "john@gmail.com", "+6911111111111");
		UserProperties user2 = new UserProperties(2, "tenant","Jane", "Middle Name", "Last-Name-Jane", "jane@gmail.com", "+6911111111112");
		
		Location location = new Location(1,1,"Town1","City1","Region1","Country1");
		Location location2 = new Location(2,2,"Town2","City2","Region2","Country2");
		Location location3 = new Location(3,3,"Town3","City3","Region3","Country3");
		
		Amenities amenities = new Amenities(1, 1, 2, 1, 2, "Wifi, Cable");
		Amenities amenities2 = new Amenities(2, 1, 2, 2, 1, "Gym, Pool");
		Amenities amenities3 = new Amenities(3, 3, 1, 1, 1, "Microwave, Ref");
		
		Reviews review1 = new Reviews(1, 1, 3, 4, "Awesome host", "2020-11-01");
		Reviews review2 = new Reviews(2, 1, 4, 3, "So-so", "2020-11-03");
		List<Reviews> listReviews = new ArrayList<Reviews>();
		listReviews.add(review1);
		listReviews.add(review2);
		
		Properties property1 = new Properties(1, user1, location, amenities, listReviews, "bungalow", "Bungalow Property", "A Bungalow Property", (float)3999.00, "2020-11-01 11:00:00", "2020-11-01 11:00:00");
		Properties property2 = new Properties(2, user1, location2, amenities2, listReviews, "bungalow", "Bungalow Property2", "A Bungalow Property2", (float)3999.00, "2020-11-01 11:00:00", "2020-11-01 11:00:00");
		Properties property3 = new Properties(3, user2, location3, amenities3, listReviews, "bungalow", "Bungalow Property3", "A Bungalow Property3", (float)3999.00, "2020-11-01 11:00:00", "2020-11-01 11:00:00");		
		
		List<Properties> list = new ArrayList<Properties>();
		list.add(property1);
		list.add(property2);
		list.add(property3);
		
		doReturn(list).when(service).getUserProperties(2);

		mockMvc.perform(get("/users/{id_users}/properties",2))

			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			
			.andExpect(jsonPath("$.[0].id_property").value(1))
			.andExpect(jsonPath("$.[0].users.id_user").value(1))
			.andExpect(jsonPath("$.[0].location.id_location").value(1))
			.andExpect(jsonPath("$.[0].amenities.id_amenity").value(1))
			.andExpect(jsonPath("$.[0].type").value("bungalow"))
			.andExpect(jsonPath("$.[0].name").value("Bungalow Property"))
			.andExpect(jsonPath("$.[0].description").value("A Bungalow Property"))
			.andExpect(jsonPath("$.[0].price").value(3999.00))
			.andExpect(jsonPath("$.[0].created_datetime").value("2020-11-01 11:00:00"))
			.andExpect(jsonPath("$.[0].updated_datetime").value("2020-11-01 11:00:00"))
			.andExpect(jsonPath("$.[1].id_property").value(2))
			.andExpect(jsonPath("$.[1].users.id_user").value(1))
			.andExpect(jsonPath("$.[1].location.id_location").value(2))
			.andExpect(jsonPath("$.[1].amenities.id_amenity").value(2))
			.andExpect(jsonPath("$.[1].type").value("bungalow"))
			.andExpect(jsonPath("$.[1].name").value("Bungalow Property2"))
			.andExpect(jsonPath("$.[1].description").value("A Bungalow Property2"))
			.andExpect(jsonPath("$.[1].price").value(3999.00))
			.andExpect(jsonPath("$.[1].created_datetime").value("2020-11-01 11:00:00"))
			.andExpect(jsonPath("$.[1].updated_datetime").value("2020-11-01 11:00:00"));
	}
	
	@Test
	@DisplayName("PUT /properties/{id_property} SUCCESS")
	void updatePropertySuccess() throws Exception{
		
		Properties propertyFind = new Properties(1, 2, "bungalow", "Bungalow Property", "A Bungalow Property", (float)3999.00, "2020-11-01 11:00:00", "2020-11-01 11:00:00");
		Properties propertyPut = new Properties(1, 2, "bungalow", "Bungalow Property2", "A Bungalow Property2", (float)3999.00, "2020-11-01 11:00:00", "2020-11-01 11:10:00");
		
		ResponseEntity<Properties> responseFind = new ResponseEntity<Properties>(propertyFind, HttpStatus.OK);
		ResponseEntity<Properties> response = new ResponseEntity<Properties>(propertyPut, HttpStatus.OK);
		
		doReturn(responseFind).when(service).getProperty(1);
		doReturn(response).when(service).updateProperty(2, 1, propertyPut);
		
		mockMvc.perform(put("/users/{id_user}/properties/{id_property}",2,1)
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(propertyFind)))
				
				.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("DELETE /users/{id_user}/properties/{id_property} SUCCESS")
	void deleteProperty() throws Exception{
		Properties property = new Properties(1, "bungalow", "Bungalow Property", "A Bungalow Property", (float)3999.00, "2020-11-01 11:00:00");
		ResponseEntity<String> response = new ResponseEntity<String>("Property Deleted",HttpStatus.OK);
		
		doReturn(response).when(service).deleteProperty(1,1);
		
		mockMvc.perform(delete("/users/{id_user}/properties/{id_property}",1,1)
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(property)))
			
				.andExpect(status().isOk())
				.equals(response.getStatusCodeValue() == 201);
	}
	
	
	public String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }	
	
}
