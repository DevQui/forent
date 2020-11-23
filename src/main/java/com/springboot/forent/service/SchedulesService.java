package com.springboot.forent.service;

import java.util.List;

import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.forent.exception.DataNotFoundException;
import com.springboot.forent.exception.NoDataFoundException;
import com.springboot.forent.model.Schedules;
import com.springboot.forent.repository.SchedulesRepository;

@Service
public class SchedulesService {
	
	@Autowired
	private SchedulesRepository schedulesRespository;
	@Ignore
	public List<Schedules> listAllSchedules(){
		List<Schedules> schedules = (List<Schedules>) schedulesRespository.findAll();
		System.out.println(schedules);
		if(!schedules.isEmpty()) {
			return schedules;
		}else {
			throw new NoDataFoundException();
		}
		 
	}
	
	public Schedules saveSchedule(Schedules schedule) {
		return schedulesRespository.save(schedule);
    }
	@Ignore
    public Schedules getSchedule(Integer id) {
    	try {
    		return schedulesRespository.findById(id).get();
    	}catch(Exception ex) {
    		throw new DataNotFoundException(id);
    	}
        
    }

    public String deleteSchedule(Integer id) {
    	schedulesRespository.deleteById(id);
        return "Schedule Deleted";
    }
}