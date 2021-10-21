package com.springboot.forent.service;

import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.forent.exception.DataNotFoundException;
import com.springboot.forent.exception.NoDataFoundException;
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
	@DisplayName("TEST listAllSchedules")
	void listAllSchedules() throws Exception {
		Schedules schedule1 = new Schedules(1, 1, 1, 0, "2020-11-01", "2020-11-02");
		Schedules schedule2 = new Schedules(2, 1, 2, 0, "2020-11-03", "2020-11-04");
		Schedules schedule3 = new Schedules(3, 2, 3, 0, "2020-11-01", "2020-11-04");
		
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
	@DisplayName("TEST getScheduleById")
	void getScheduleById() throws Exception{
		Schedules schedule1 = new Schedules(1, 1, 1, 0, "2020-11-01", "2020-11-02");
		Schedules schedule2 = new Schedules(2, 1, 3, 0, "2020-11-03", "2020-11-04");
		Schedules schedule3 = new Schedules(3, 2, 3, 0, "2020-11-01", "2020-11-04");
		
		List<Schedules> list = new ArrayList<Schedules>();
		list.add(schedule1);
		list.add(schedule2);
		list.add(schedule3);
		
		doReturn(schedule1).when(repo).getScheduleById(1);
		
		Schedules schedule = service.getScheduleById(1);
		
		Assertions.assertEquals(schedule.getId_schedule(),schedule1.getId_schedule());
		Assertions.assertEquals(schedule.getId_property(),schedule1.getId_property());
		Assertions.assertEquals(schedule.getId_user(),schedule1.getId_user());
		Assertions.assertEquals(schedule.getSchedule_date_from(), schedule1.getSchedule_date_from());
		Assertions.assertEquals(schedule.getSchedule_date_to(), schedule1.getSchedule_date_to());
	}
	
	@Test
	@DisplayName("TEST listAllPropertySchedules")
	void listAllPropertySchedules() throws Exception{
		Schedules schedule1 = new Schedules(1, 1, 1, 0, "2020-11-01", "2020-11-02");
		Schedules schedule2 = new Schedules(2, 1, 3, 0, "2020-11-03", "2020-11-04");
		Schedules schedule3 = new Schedules(3, 2, 3, 0, "2020-11-01", "2020-11-04");
		
		List<Schedules> list = new ArrayList<Schedules>();
		list.add(schedule1);
		list.add(schedule2);
		list.add(schedule3);
		
		doReturn(list).when(repo).findPropertySchedules(1);
		
		List<Schedules> schedule = service.listAllPropertySchedules(1);
		
		Assertions.assertEquals(schedule.get(0).getId_schedule(),schedule1.getId_schedule());
		Assertions.assertEquals(schedule.get(0).getId_property(),schedule1.getId_property());
		Assertions.assertEquals(schedule.get(0).getId_user(),schedule1.getId_user());
		Assertions.assertEquals(schedule.get(0).getSchedule_date_from(), schedule1.getSchedule_date_from());
		Assertions.assertEquals(schedule.get(0).getSchedule_date_to(), schedule1.getSchedule_date_to());
	}
	
	
	@Test
	@DisplayName("TEST listAllUserSchedules")
	void listAllUserSchedules() throws Exception{
		Schedules schedule1 = new Schedules(1, 1, 1, 0, "2020-11-01", "2020-11-02");
		Schedules schedule2 = new Schedules(2, 1, 3, 0, "2020-11-03", "2020-11-04");
		Schedules schedule3 = new Schedules(3, 2, 3, 0, "2020-11-01", "2020-11-04");
		
		List<Schedules> list = new ArrayList<Schedules>();
		list.add(schedule1);
		list.add(schedule2);
		list.add(schedule3);
		
		doReturn(list).when(repo).findUserSchedules(schedule1.getId_user());
		
		List<Schedules> schedule = service.listAllUserSchedules(schedule1.getId_user());
		
		Assertions.assertEquals(schedule.get(0).getId_schedule(),schedule1.getId_schedule());
		Assertions.assertEquals(schedule.get(0).getId_property(),schedule1.getId_property());
		Assertions.assertEquals(schedule.get(0).getId_user(),schedule1.getId_user());
		Assertions.assertEquals(schedule.get(0).getSchedule_date_from(), schedule1.getSchedule_date_from());
		Assertions.assertEquals(schedule.get(0).getSchedule_date_to(), schedule1.getSchedule_date_to());
	}
	
	@Test
	@DisplayName("TEST getUserScheduleDetails")
	void getUserScheduleDetails() throws Exception{
		Schedules schedule1 = new Schedules(1, 1, 1, 0, "2020-11-01", "2020-11-02");
		Schedules schedule2 = new Schedules(2, 1, 3, 0, "2020-11-03", "2020-11-04");
		Schedules schedule3 = new Schedules(3, 2, 3, 0, "2020-11-01", "2020-11-04");
		
		List<Schedules> list = new ArrayList<Schedules>();
		list.add(schedule1);
		list.add(schedule2);
		list.add(schedule3);
		
		doReturn(schedule1).when(repo).getUserScheduleDetails(schedule1.getId_user(), schedule1.getId_schedule());
		
		ResponseEntity<Schedules> schedule = service.getUserScheduleDetails(schedule1.getId_user(), schedule1.getId_schedule());
		
		Assertions.assertEquals(schedule.getBody().getId_schedule(),schedule1.getId_schedule());
		Assertions.assertEquals(schedule.getBody().getId_property(),schedule1.getId_property());
		Assertions.assertEquals(schedule.getBody().getId_user(),schedule1.getId_user());
		Assertions.assertEquals(schedule.getBody().getSchedule_date_from(), schedule1.getSchedule_date_from());
		Assertions.assertEquals(schedule.getBody().getSchedule_date_to(), schedule1.getSchedule_date_to());
	}
	
	@Test
	@DisplayName("TEST saveSchedule")
	void saveSchedule() throws Exception{
		Schedules schedule = new Schedules(3, 2, 3, 0, "2020-11-01", "2020-11-04");
		Integer savedStatus = 1;
		doReturn(savedStatus).when(repo).saveSchedule(schedule.getId_property(), schedule.getId_schedule(), schedule.getSchedule_date_from(), schedule.getSchedule_date_to());
		
		ResponseEntity<String> addedSchedule = service.saveSchedule(3,2,schedule);
		
		Assertions.assertEquals(addedSchedule.getStatusCodeValue(), 201);	
	}
	
	@Test
	@DisplayName("TEST updateSchedule")
	void updateSchedule() throws Exception{
		Schedules schedule = new Schedules(1, 1, 1, 0, "2020-11-01", "2020-11-04");
		Integer updatedStatus = 1;
		doReturn(updatedStatus).when(repo).acceptSchedule(schedule.getId_property(),schedule.getId_schedule(), updatedStatus);
		
		ResponseEntity<String> updatedSchedule = service.updateSchedule(schedule.getId_user(), schedule.getId_property(),schedule.getId_schedule());
		
		Assertions.assertEquals(updatedSchedule.getStatusCodeValue(), 200);	
	}
	
	@Test
	@DisplayName("TEST getPropertySchedule")
	void getPropertySchedule() throws Exception{
		Schedules schedule1 = new Schedules(1, 1, 1, 0, "2020-11-01", "2020-11-02");
		Schedules schedule2 = new Schedules(2, 1, 3, 0, "2020-11-03", "2020-11-04");
		Schedules schedule3 = new Schedules(3, 2, 3, 0, "2020-11-01", "2020-11-04");
		
		List<Schedules> list = new ArrayList<Schedules>();
		list.add(schedule1);
		list.add(schedule2);
		list.add(schedule3);
		
		doReturn(schedule1).when(repo).getPropertySchedule(schedule1.getId_property(), schedule1.getId_schedule());
		
		Schedules schedule = service.getPropertySchedule(schedule1.getId_property(), schedule1.getId_schedule());
		
		Assertions.assertEquals(schedule.getId_schedule(),schedule1.getId_schedule());
		Assertions.assertEquals(schedule.getId_property(),schedule1.getId_property());
		Assertions.assertEquals(schedule.getId_user(),schedule1.getId_user());
		Assertions.assertEquals(schedule.getSchedule_date_from(), schedule1.getSchedule_date_from());
		Assertions.assertEquals(schedule.getSchedule_date_to(), schedule1.getSchedule_date_to());
	}
	
	@Test
	@DisplayName("TEST deleteSchedule")
	void deleteSchedule() throws Exception{
		Schedules schedule = new Schedules(3, 2, 3, 0, "2020-11-01", "2020-11-04");
		Integer deletedStatus = 1;
		doReturn(deletedStatus).when(repo).deleteSchedule(schedule.getId_user(), schedule.getId_schedule());
		
		ResponseEntity<String> deletedSchedule = service.deleteSchedule(schedule.getId_user(), schedule.getId_schedule());
		
		Assertions.assertEquals(deletedSchedule.getStatusCodeValue(), 200);	
	}
	
	@Test
	@DisplayName("TEST findAllWithUserInfoNORESULT")
	void listAllSchedulesNORESULT() throws NoDataFoundException {
		Assertions.assertThrows(NoDataFoundException.class, () -> {
			service.listAllSchedules();
		  });
	}
	
	@Test
	@DisplayName("TEST listAllPropertySchedulesNORESULT")
	void listAllPropertySchedulesNORESULT() throws NoDataFoundException {
		Assertions.assertThrows(NoDataFoundException.class, () -> {
			service.listAllPropertySchedules(1);
		  });
	}
	
	@Test
	@DisplayName("TEST listAllUserSchedulesNORESULT")
	void listAllUserSchedulesNORESULT() throws NoDataFoundException {
		Assertions.assertThrows(NoDataFoundException.class, () -> {
			service.listAllUserSchedules(1);
		  });
	}
	
	@Test
	@DisplayName("TEST getUserScheduleDetailsNOTFOUND")
	void getUserScheduleDetailsNOTFOUND() throws DataNotFoundException {
		Assertions.assertThrows(DataNotFoundException.class, () -> {
			service.getUserScheduleDetails(1,1);
		  });
	}
	
	@Test
	@DisplayName("TEST saveScheduleNOTFOUND")
	void saveScheduleNOTFOUND() throws DataNotFoundException {
		Assertions.assertThrows(DataNotFoundException.class, () -> {
			service.saveSchedule(1,1, new Schedules());
		  });
	}
	
	@Test
	@DisplayName("TEST updateScheduleNOTFOUND")
	void updateScheduleNOTFOUND() throws DataNotFoundException {
		Assertions.assertThrows(DataNotFoundException.class, () -> {
			service.updateSchedule(1,1,1);
		  });
	}
	
	@Test
	@DisplayName("TEST deleteScheduleNOTFOUND")
	void deleteScheduleNOTFOUND() throws DataNotFoundException {
		Assertions.assertThrows(DataNotFoundException.class, () -> {
			service.deleteSchedule(1,1);
		  });
	}
	
	@Test
	@DisplayName("TEST getScheduleByIdNOTFOUND")
	void getScheduleByIdNOTFOUND() throws DataNotFoundException {
		Assertions.assertThrows(DataNotFoundException.class, () -> {
			service.getScheduleById(1);
		  });
	}
	
	@Test
	@DisplayName("TEST getPropertyScheduleNOTFOUND")
	void getPropertyScheduleNOTFOUND() throws DataNotFoundException {
		Assertions.assertThrows(DataNotFoundException.class, () -> {
			service.getPropertySchedule(1,1);
		  });
	}
}
