package com.springboot.forent.controller;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import com.springboot.forent.model.Schedules;
import com.springboot.forent.model.Users;
import com.springboot.forent.service.SchedulesService;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@WebMvcTest(SchedulesController.class)
class SchedulesControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private SchedulesService service;
	
	@Test
	@DisplayName("GET /schedules WITH RESULT")
	void getSchedulesListWithResult() throws Exception{
		Schedules schedule1 = new Schedules(1, 1, 1, "2020-11-01", "2020-11-02");
		Schedules schedule2 = new Schedules(2, 1, 2, "2020-11-03", "2020-11-04");
		Schedules schedule3 = new Schedules(3, 2, 3, "2020-11-01", "2020-11-04");
		
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
	@DisplayName("GET /schedules WITH NO RESULT")
	void getSchedulesListNoResult() throws Exception {
		doReturn(new ArrayList<Users>()).when(service).listAllSchedules();

		mockMvc.perform(get("/schedules"))

			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))

			.andExpect(content().string("[]"));
	}
	
	@Test
	@DisplayName("GET /schedules/3 is FOUND")
	void getSchedulesByIdFound() throws Exception {
		Schedules schedule1 = new Schedules(1, 1, 1, "2020-11-01", "2020-11-02");
		Schedules schedule2 = new Schedules(2, 1, 2, "2020-11-03", "2020-11-04");
		Schedules schedule3 = new Schedules(3, 2, 3, "2020-11-01", "2020-11-04");
		
		List<Schedules> list = new ArrayList<Schedules>();
		list.add(schedule1);
		list.add(schedule2);
		list.add(schedule3);
	
		doReturn(schedule3).when(service).getSchedule(3);

		mockMvc.perform(get("/schedules/{id}",3))

			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))

			.andExpect(jsonPath("$.id_schedule").value(3))
			.andExpect(jsonPath("$.id_property").value(2))
			.andExpect(jsonPath("$.id_user").value(3))
			.andExpect(jsonPath("$.schedule_date_from").value("2020-11-01"))
			.andExpect(jsonPath("$.schedule_date_to").value("2020-11-04"));
	}
	
	
	@Test
	@DisplayName("POST /schedules is SUCCESSFUL")
	void addScheduleSuccess() throws Exception {
		// Mocked the users and the service
		Schedules schedule1 = new Schedules(1, 1, 1, "2020-11-01", "2020-11-02");
		doReturn(schedule1).when(service).saveSchedule(schedule1);	
		
		mockMvc.perform(post("/schedules")
			.contentType(MediaType.APPLICATION_JSON)
			.content(asJsonString(schedule1)))
		
			.andExpect(status().isCreated())
			.andExpect(header().string(HttpHeaders.LOCATION,"/schedules"));
	}
	
	@Test
	@DisplayName("DELETE /schedules/1 SUCCESS")
	void deleteSchedule() throws Exception{
		Schedules schedule = new Schedules(1, 1, 1, "2020-11-01", "2020-11-02");
		doReturn("Schedule Deleted").when(service).deleteSchedule(1);
		
		mockMvc.perform(delete("/schedules/1")
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
