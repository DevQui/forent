package com.springboot.forent.service;

import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.forent.model.Favorites;
import com.springboot.forent.model.Schedules;
import com.springboot.forent.repository.SchedulesRepository;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@WebMvcTest(SchedulesService.class)
class SchedulesServiceTest {
	
	@Autowired
	SchedulesService service;
	
	@MockBean
	SchedulesRepository repo;
	
	@Test
	@DisplayName("TEST getSchedulesHasResult")
	void getSchedulesHasResult() throws Exception {
		Schedules schedule1 = new Schedules(1, 1, 1, "2020-11-01", "2020-11-02");
		Schedules schedule2 = new Schedules(2, 1, 2, "2020-11-03", "2020-11-04");
		Schedules schedule3 = new Schedules(3, 2, 3, "2020-11-01", "2020-11-04");
		
		List<Schedules> list = new ArrayList<Schedules>();
		list.add(schedule1);
		list.add(schedule2);
		list.add(schedule3);
		
		doReturn(list).when(repo).findAll();
		// Call service
		List<Schedules> returnedList = (List<Schedules>) service.listAllSchedules();
		// Validate
		Assertions.assertFalse(returnedList.isEmpty(), "No result.");
		Assertions.assertSame(returnedList.get(0), schedule1, "User should be the same.");
		Assertions.assertEquals(returnedList.get(2).getId_schedule(), 3);
	}
	
	@Test
	@DisplayName("TEST getSchedulesByID")
	void getSchedulesByID() throws Exception{
		Schedules schedule1 = new Schedules(1, 1, 1, "2020-11-01", "2020-11-02");
		doReturn(Optional.of(schedule1)).when(repo).findById(1);
		
		Schedules schedule = service.getSchedule(1);
		
		Assertions.assertEquals(schedule.getId_schedule(),1);
		Assertions.assertEquals(schedule.getId_property(),1);
		Assertions.assertEquals(schedule.getId_user(),1);
		Assertions.assertEquals(schedule.getSchedule_date_from(),"2020-11-01");
		Assertions.assertEquals(schedule.getSchedule_date_to(), "2020-11-02");
	}
	
	@Test
	@DisplayName("TEST saveSchedule")
	void saveSchedule() throws Exception{
		Schedules schedule = new Schedules(3, 2, 3, "2020-11-01", "2020-11-04");
		doReturn(schedule).when(repo).save(schedule);
		
		Schedules addedSchedule = service.saveSchedule(schedule);
		
		Assertions.assertEquals(addedSchedule.getId_schedule(),3);
		Assertions.assertEquals(addedSchedule.getId_property(),2);
		Assertions.assertEquals(addedSchedule.getId_user(),3);
		Assertions.assertEquals(addedSchedule.getSchedule_date_from(),"2020-11-01");
		Assertions.assertEquals(addedSchedule.getSchedule_date_to(), "2020-11-04");	
	}
	
	@Test
	@DisplayName("TEST deleteSchedule")
	void deleteSchedule() throws Exception{
		Schedules schedule = new Schedules(3, 2, 3, "2020-11-01", "2020-11-04");
				
		repo.delete(schedule);
		String response = service.deleteSchedule(1);
		
		Assertions.assertEquals(response, "Schedule Deleted");
	}

}
