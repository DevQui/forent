package com.springboot.forent;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.springboot.forent.service.SchedulesService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ForentApplication.class)
@AutoConfigureMockMvc
class SchedulesTest {

	@Autowired
	protected MockMvc mvc;
	
	@Autowired
	SchedulesService service;
	
	@Test
	void test() {
		//fail("Not yet implemented");
	}
	
	@Test
	@Rollback(false)
	public void testPostSchedule() throws Exception {
		/*Schedules schedule = new Schedules(2, 7, "2020-11-05", "2020-11-06");
		service.saveSchedule(schedule);
	    assertEquals(schedule.getSchedule_date_from(), "2020-11-05");*/
	}
	
	@Test
	public void testGetSchedule() throws Exception{
		/*Schedules schedule = service.getSchedule(1);
		assertEquals(schedule.getId_user(), 5);*/
	}
	
	@Test
	public void testDeleteSchedule() throws Exception{
		/*String uri = "http://localhost:8080/schedules/4";
	    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
	    int status = mvcResult.getResponse().getStatus();
	    assertEquals(200, status);*/
	}
}
