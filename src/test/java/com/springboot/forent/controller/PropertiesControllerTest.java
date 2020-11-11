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

import com.springboot.forent.model.Properties;
import com.springboot.forent.model.Users;
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
	@DisplayName("GET /users WITH RESULT")
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
	@DisplayName("GET /proerties/3 is FOUND")
	void getPropertyByIdFound() throws Exception {
		// Mocked the users and the service
		Properties property1 = new Properties(1, "bungalow", "Bungalow Property", "A Bungalow Property", (float)3999.00, "2020-11-01 11:00:00");
		Properties property2 = new Properties(2, "apartment", "Apartment Property", "An Apartment Property", (float)1999.00, "2020-11-01 11:00:00");
		Properties property3 = new Properties(3, "condominium", "Condominium Property", "A Condominium Property", (float)2999.00, "2020-11-01 11:00:00");

		List<Properties> list = new ArrayList<Properties>();
		list.add(property1);
		list.add(property2);
		list.add(property3);
	
		doReturn(property3).when(service).getProperty(3);

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
}
