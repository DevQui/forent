package com.springboot.forent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.forent.exception.DataNotFoundException;
import com.springboot.forent.exception.NoDataFoundException;
import com.springboot.forent.model.Schedules;
import com.springboot.forent.repository.SchedulesRepository;

@Service
public class SchedulesService {
	
	@Autowired
	private SchedulesRepository schedulesRepository;
	
	public List<Schedules> listAllSchedules(){
		List<Schedules> schedules = (List<Schedules>) schedulesRepository.findAll();
		if(!schedules.isEmpty()) {
			return schedules;
		}else {
			throw new NoDataFoundException();
		}
	}
	
	public List<Schedules> listAllPropertySchedules(Integer id_property) {
		List<Schedules> propertySchedules = schedulesRepository.findPropertySchedules(id_property);
		if(!propertySchedules.isEmpty()) {
			return propertySchedules;
		}else {
			throw new NoDataFoundException();
		}
		
	}
	
	public List<Schedules> listAllUserSchedules(Integer id_user) {
		List<Schedules> userSchedules = schedulesRepository.findUserSchedules(id_user);
		if(!userSchedules.isEmpty()) {
			return userSchedules;
		}else {
			throw new NoDataFoundException();
		}
		
	}
	
    public ResponseEntity<Schedules> getUserScheduleDetails(Integer id_user, Integer id_schedule) {
		Schedules schedule = schedulesRepository.getUserScheduleDetails(id_user, id_schedule);
		if(schedule != null) {
    		return new ResponseEntity<Schedules>(schedule, HttpStatus.OK);
		}else {
    		throw new DataNotFoundException(id_schedule);
    	}
    }

    
    public ResponseEntity<String> saveSchedule(Integer id_user, Integer id_property, Schedules schedule) {
		Integer saved = schedulesRepository.saveSchedule(id_user, id_property, schedule.getSchedule_date_from(), schedule.getSchedule_date_to());
		if(saved > 0) {
			return new ResponseEntity<String>("Successfully Added Schedule", HttpStatus.CREATED);
		}else {
			throw new DataNotFoundException(id_property);
		}
    }
    
    public ResponseEntity<String> updateSchedule(Integer id_user, Integer id_property, Integer id_schedule) {
		Integer updated = schedulesRepository.acceptSchedule(id_property, id_user, id_schedule, 1);
		if(updated > 0) {
			return new ResponseEntity<String>("Booking Schedule Request Accepted", HttpStatus.OK);
		}else {
			throw new DataNotFoundException(id_schedule);
		}
	}  
    
    public ResponseEntity<String> deleteSchedule(Integer id_user, Integer id_property, Integer id_schedule) {
        Integer isDeleted = schedulesRepository.deleteSchedule(id_user, id_property, id_schedule);
    	if(isDeleted > 0) {
    		return new ResponseEntity<String>("Schedule Deleted", HttpStatus.OK);
    	}else{
    		throw new DataNotFoundException(id_schedule);
    	}
    }

	public Schedules getScheduleById(Integer id_schedule) {
		Schedules schedule = schedulesRepository.getScheduleById(id_schedule);
		if(schedule != null) {
			return schedule;
		}else {
			throw new DataNotFoundException(id_schedule);
		}
	}

	public Schedules getPropertySchedule(Integer id_property, Integer id_schedule) {
		Schedules schedules = schedulesRepository.getPropertySchedule(id_property, id_schedule);
		if(schedules != null) {
			return schedules;
		}else {
			throw new DataNotFoundException(id_schedule);
		}
	}  
}