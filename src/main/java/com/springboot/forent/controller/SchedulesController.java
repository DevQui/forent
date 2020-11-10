package com.springboot.forent.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@GetMapping("/schedules")
    public List<Schedules> list() {
        return schedulesService.listAllSchedules();
    }
	
	
	@GetMapping("/schedules/{id}")
    public ResponseEntity<Schedules> get(@PathVariable Integer id) {
        try {
            Schedules schedule = schedulesService.getSchedule(id);
            return new ResponseEntity<Schedules>(schedule, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Schedules>(HttpStatus.NOT_FOUND);
        }
	}
	
	@PostMapping("/schedules")
	public void add(@RequestBody Schedules schedule) {
        schedulesService.saveSchedule(schedule);
    }	
	
    @DeleteMapping("/schedules/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        String response = schedulesService.deleteSchedule(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
