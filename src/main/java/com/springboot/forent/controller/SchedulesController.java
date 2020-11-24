package com.springboot.forent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.forent.model.Schedules;
import com.springboot.forent.service.SchedulesService;

@RestController
public class SchedulesController {
	@Autowired
    private SchedulesService schedulesService;

	@GetMapping("/schedules")
    public List<Schedules> list() {
        return schedulesService.listAllSchedules();
	}
	
	@GetMapping("/schedules/{id_schedule}")
    public Schedules getScheduleById(@PathVariable Integer id_schedule) {
        return schedulesService.getScheduleById(id_schedule);
	}
	
	//as a host user, I want to know the schedules of my property/properties
	//(list of schedules with the user)
	@GetMapping("/properties/{id_property}/schedules")
    public List<Schedules> listPropertySchedules(@PathVariable Integer id_property) {
        return schedulesService.listAllPropertySchedules(id_property);
    }
	
	@GetMapping("/properties/{id_property}/schedules/{id_schedule}")
    public Schedules getPropertySchedule(@PathVariable Integer id_property, @PathVariable Integer id_schedule) {
        return schedulesService.getPropertySchedule(id_property, id_schedule);
    }
	
	//as a tenant user, I want to know my schedules
	//(list of schedules with the properties)
	@GetMapping("/users/{id_user}/schedules")
    public List<Schedules> listUserSchuedles(@PathVariable Integer id_user) {
        return schedulesService.listAllUserSchedules(id_user);
    }
	
	//as a tenant user, I want to get the details of my schedule
	//(get: property id,)
	@GetMapping("/users/{id_user}/schedules/{id_schedule}")
    public ResponseEntity<Schedules> getUserScheduleDetails(@PathVariable Integer id_user, @PathVariable Integer id_schedule) {
          return schedulesService.getUserScheduleDetails(id_user, id_schedule);
	}
	
	@PostMapping("/users/{id_user}/properties/{id_property}/schedules")
	public ResponseEntity<String> add(@PathVariable Integer id_user, @PathVariable Integer id_property, @RequestBody Schedules schedules) {
		return schedulesService.saveSchedule(id_user, id_property, schedules);
    }
	
	@PatchMapping("/users/{id_user}/properties/{id_property}/schedules/{id_schedule}")
    public ResponseEntity<String> updateSchedule(@PathVariable Integer id_user, @PathVariable Integer id_property, @PathVariable Integer id_schedule) { 
    	return schedulesService.updateSchedule(id_user, id_property, id_schedule);
    }
	 
    @DeleteMapping("/users/{id_user}/properties/{id_property}/schedules/{id_schedule}")
    public ResponseEntity<String> delete(@PathVariable Integer id_user, @PathVariable Integer id_property, @PathVariable Integer id_schedule) {
        return schedulesService.deleteSchedule(id_user, id_property, id_schedule);
    }
}
