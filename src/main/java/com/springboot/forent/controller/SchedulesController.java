package com.springboot.forent.controller;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
	//"/properties/{id_property}/schedules" (list of schedules with the user)
	//"/users/{id_user}/schedules" (list of schedules with the properties)
	@GetMapping("/schedules")
    public List<Schedules> list() {
        return schedulesService.listAllSchedules();
    }
	//"/users/{id_user}/schedules/{id_schedule}"(get: property id,)
	@GetMapping("/schedules/{id}")
    public ResponseEntity<Schedules> get(@PathVariable Integer id) {
        try {
            Schedules schedule = schedulesService.getSchedule(id);
            return new ResponseEntity<Schedules>(schedule, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Schedules>(HttpStatus.NOT_FOUND);
        }
	}
	
	@PostMapping("users/{id_users}/properties/{id_property}/schedules")
	public ResponseEntity<Schedules> add(@PathVariable Integer id_users, @PathVariable Integer id_property, @RequestBody Schedules schedule) {
		try {
			HttpHeaders header = new HttpHeaders();
			header.setLocation(new URI("/schedules"));
			Schedules response = schedulesService.saveSchedule(schedule);
			return new ResponseEntity<Schedules>(response,header,HttpStatus.CREATED);
		}catch(Exception ex) {
			return new ResponseEntity<>(schedule,HttpStatus.PRECONDITION_REQUIRED);
		}
        
    }	
	
    @DeleteMapping("users/{id_user}/properties/{id_property}/schedules/{id_schedule}")
    public ResponseEntity<String> delete(@PathVariable Integer id_users, @PathVariable Integer id_property, @PathVariable Integer id_schedule) {
        String response = schedulesService.deleteSchedule(id_schedule);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
