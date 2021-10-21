package com.springboot.forent.controller;

import static org.mockito.Mockito.doReturn;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
import org.mockito.Mockito;
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
import com.springboot.forent.model.Schedules;
import com.springboot.forent.service.SchedulesService;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@WebMvcTest(SchedulesController.class)
//@ComponentScan(basePackageClasses = { KeycloakSecurityComponents.class, KeycloakSpringBootConfigResolver.class })
class SchedulesControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private SchedulesService service;
		
	@Test
	@DisplayName("GET /schedules WITH RESULT")
	@WithMockUser(roles = "tenant")
	void listAllSchedules() throws Exception{
		Schedules schedule1 = new Schedules(1, 1, 1, 0, "2020-11-01", "2020-11-02");
		Schedules schedule2 = new Schedules(2, 1, 2, 0, "2020-11-03", "2020-11-04");
		Schedules schedule3 = new Schedules(3, 2, 3, 0, "2020-11-01", "2020-11-04");
		
		List<Schedules> list = new ArrayList<Schedules>();
		list.add(schedule1);
		list.add(schedule2);
		list.add(schedule3);
		
		doReturn(list).when(service).listAllSchedules();

		mockMvc.perform(get("/schedules"))

			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))

			.andExpect(jsonPath("$.[0].id_schedule").value(1))
			.andExpect(jsonPath("$.[0].id_property").value(1))
			.andExpect(jsonPath("$.[0].id_user").value(1))
			.andExpect(jsonPath("$.[0].schedule_date_from").value("2020-11-01"))
			.andExpect(jsonPath("$.[0].schedule_date_to").value("2020-11-02"))
			.andExpect(jsonPath("$.[1].id_schedule").value(2))
			.andExpect(jsonPath("$.[1].id_property").value(1))
			.andExpect(jsonPath("$.[1].id_user").value(2))
			.andExpect(jsonPath("$.[1].schedule_date_from").value("2020-11-03"))
			.andExpect(jsonPath("$.[1].schedule_date_to").value("2020-11-04"))
			.andExpect(jsonPath("$.[2].id_schedule").value(3))
			.andExpect(jsonPath("$.[2].id_property").value(2))
			.andExpect(jsonPath("$.[2].id_user").value(3))
			.andExpect(jsonPath("$.[2].schedule_date_from").value("2020-11-01"))
			.andExpect(jsonPath("$.[2].schedule_date_to").value("2020-11-04"));
	}
	
	@Test
	@DisplayName("GET /schedules/{id_schedules} is FOUND")
	@WithMockUser(roles = "tenant")
	void getScheduleById() throws Exception {
		Schedules schedule1 = new Schedules(1, 1, 1, 0, "2020-11-01", "2020-11-02");
		Schedules schedule2 = new Schedules(2, 1, 2, 0, "2020-11-03", "2020-11-04");
		Schedules schedule3 = new Schedules(3, 2, 3, 0, "2020-11-01", "2020-11-04");
		
		List<Schedules> list = new ArrayList<Schedules>();
		list.add(schedule1);
		list.add(schedule2);
		list.add(schedule3);
	
		doReturn(schedule1).when(service).getScheduleById(1);

		mockMvc.perform(get("/schedules/{id_schedule}",1))

			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))

			.andExpect(jsonPath("$.id_schedule").value(1))
			.andExpect(jsonPath("$.id_property").value(1))
			.andExpect(jsonPath("$.id_user").value(1))
			.andExpect(jsonPath("$.status").value(0))
			.andExpect(jsonPath("$.schedule_date_from").value("2020-11-01"))
			.andExpect(jsonPath("$.schedule_date_to").value("2020-11-02"));
	}
	
	@Test
	@DisplayName("GET /properties/{id_property}/schedules is FOUND")
	@WithMockUser(roles = "tenant")
	void listPropertySchedules() throws Exception {
		Schedules schedule1 = new Schedules(1, 1, 1, 0, "2020-11-01", "2020-11-02");
		Schedules schedule2 = new Schedules(2, 1, 2, 0, "2020-11-03", "2020-11-04");
		Schedules schedule3 = new Schedules(3, 2, 3, 0, "2020-11-01", "2020-11-04");
		
		List<Schedules> list = new ArrayList<Schedules>();
		list.add(schedule1);
		list.add(schedule2);
		list.add(schedule3);
	
		doReturn(list).when(service).listAllPropertySchedules(1);

		mockMvc.perform(get("/properties/{id_property}/schedules",1))

			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))

			.andExpect(jsonPath("$.[0].id_schedule").value(1))
			.andExpect(jsonPath("$.[0].id_property").value(1))
			.andExpect(jsonPath("$.[0].id_user").value(1))
			.andExpect(jsonPath("$.[0].status").value(0))
			.andExpect(jsonPath("$.[0].schedule_date_from").value("2020-11-01"))
			.andExpect(jsonPath("$.[0].schedule_date_to").value("2020-11-02"));
	}
	
	@Test
	@DisplayName("GET /properties/{id_property}/schedules/{id_schedule} is FOUND")
	@WithMockUser(roles = "tenant")
	void getPropertySchedule() throws Exception {
		Schedules schedule1 = new Schedules(1, 1, 1, 0, "2020-11-01", "2020-11-02");
		Schedules schedule2 = new Schedules(2, 1, 2, 0, "2020-11-03", "2020-11-04");
		Schedules schedule3 = new Schedules(3, 2, 3, 0, "2020-11-01", "2020-11-04");
		
		List<Schedules> list = new ArrayList<Schedules>();
		list.add(schedule1);
		list.add(schedule2);
		list.add(schedule3);
			
		doReturn(schedule3).when(service).getPropertySchedule(2,3);

		mockMvc.perform(get("/properties/{id_property}/schedules/{id_schedule}",2,3))

			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))

			.andExpect(jsonPath("$.id_schedule").value(3))
			.andExpect(jsonPath("$.id_property").value(2))
			.andExpect(jsonPath("$.id_user").value(3))
			.andExpect(jsonPath("$.status").value(0))
			.andExpect(jsonPath("$.schedule_date_from").value("2020-11-01"))
			.andExpect(jsonPath("$..schedule_date_to").value("2020-11-04"));
	}
	
	@Test
	@DisplayName("GET /users/{id_user}/schedules is FOUND")
	@WithMockUser(roles = "tenant")
	void listUserSchuedles() throws Exception {
		Schedules schedule1 = new Schedules(1, 1, 1, 0, "2020-11-01", "2020-11-02");
		Schedules schedule2 = new Schedules(2, 1, 3, 0, "2020-11-03", "2020-11-04");
		Schedules schedule3 = new Schedules(3, 2, 3, 0, "2020-11-01", "2020-11-04");
		
		List<Schedules> list = new ArrayList<Schedules>();
		list.add(schedule1);
		list.add(schedule2);
		list.add(schedule3);
	
		doReturn(list).when(service).listAllUserSchedules(3);

		mockMvc.perform(get("/users/{id_user}/schedules",3))

			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))

			.andExpect(jsonPath("$.[0].id_schedule").value(1))
			.andExpect(jsonPath("$.[0].id_property").value(1))
			.andExpect(jsonPath("$.[0].id_user").value(1))
			.andExpect(jsonPath("$.[0].status").value(0))
			.andExpect(jsonPath("$.[0].schedule_date_from").value("2020-11-01"))
			.andExpect(jsonPath("$.[0].schedule_date_to").value("2020-11-02"));
	}
	
	
	@Test
	@DisplayName("GET /users/{id_user}/schedules/{id_schedule} is FOUND")
	@WithMockUser(roles = "tenant")
	void getUserScheduleDetails() throws Exception {
		Schedules schedule1 = new Schedules(1, 1, 1, 0, "2020-11-01", "2020-11-02");
		Schedules schedule2 = new Schedules(2, 1, 2, 0, "2020-11-03", "2020-11-04");
		Schedules schedule3 = new Schedules(3, 2, 3, 0, "2020-11-01", "2020-11-04");
		
		List<Schedules> list = new ArrayList<Schedules>();
		list.add(schedule1);
		list.add(schedule2);
		list.add(schedule3);
	
		ResponseEntity<Schedules> response = new ResponseEntity<Schedules>(schedule3, HttpStatus.OK);
		
		doReturn(response).when(service).getUserScheduleDetails(3,3);

		mockMvc.perform(get("/users/{id_user}/schedules/{id_schedule}",3,3))

			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))

			.andExpect(jsonPath("$.id_schedule").value(3))
			.andExpect(jsonPath("$.id_property").value(2))
			.andExpect(jsonPath("$.id_user").value(3))
			.andExpect(jsonPath("$.status").value(0))
			.andExpect(jsonPath("$.schedule_date_from").value("2020-11-01"))
			.andExpect(jsonPath("$.schedule_date_to").value("2020-11-04"));
	}
		
	@Test
	@DisplayName("POST /users/{id_user}/properties/{id_property}/schedules is SUCCESSFUL")
	@WithMockUser(roles = "tenant")
	void addScheduleSuccess() throws Exception {
		Schedules schedule1 = new Schedules(1, 1, 1, 0, "2020-11-01", "2020-11-02");
		ResponseEntity<String> response = new ResponseEntity<String>("Successfully Added Schedule", HttpStatus.CREATED);
		doReturn(response).when(service).saveSchedule(1, 1, schedule1);	
		
		mockMvc.perform(post("/users/{id_user}/properties/{id_property}/schedules",1,1)
			.with(csrf())
			.contentType(MediaType.APPLICATION_JSON)
			.content(asJsonString(schedule1)))
		
			.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("PATCH /users/{id_user}/properties/{id_property}/schedules/{id_schedule} is SUCCESSFUL")
	@WithMockUser(roles = "host")
	void updateSchedule() throws Exception {
		ResponseEntity<String> response = new ResponseEntity<String>("Booking Schedule Request Accepted", HttpStatus.OK);
		
		Mockito.when(service.updateSchedule(1,1,1)).thenReturn(response);
		mockMvc.perform(patch("/users/{id_user}/properties/{id_property}/schedules/{id_schedule}",1,1,1).with(csrf()));
	}
	
	@Test
	@DisplayName("DELETE /users/{id_user}/schedules/{id_schedule} SUCCESS")
	@WithMockUser(roles = "tenant")
	void deleteSchedule() throws Exception{
		Schedules schedule = new Schedules(1, 1, 1, 0, "2020-11-01", "2020-11-02");
		ResponseEntity<String> response = new ResponseEntity<String>("Schedule Deleted", HttpStatus.OK);
		
		doReturn(response).when(service).deleteSchedule(1, 1);
		
		mockMvc.perform(delete("/users/{id_user}/schedules/{id_schedule}",1,1)
				.with(csrf())
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(schedule)))
			
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
