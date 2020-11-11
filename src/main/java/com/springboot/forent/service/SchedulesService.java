package com.springboot.forent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.forent.model.Schedules;
import com.springboot.forent.repository.SchedulesRepository;

@Service
public class SchedulesService {
	@Autowired
	private SchedulesRepository schedulesRespository;
	
	public List<Schedules> listAllSchedules(){
		 return (List<Schedules>) schedulesRespository.findAll();
	}
	
	public Schedules saveSchedule(Schedules schedule) {
		return schedulesRespository.save(schedule);
    }

    public Schedules getSchedule(Integer id) {
        return schedulesRespository.findById(id).get();
    }

    public String deleteSchedule(Integer id) {
    	schedulesRespository.deleteById(id);
        return "Schedule Deleted";
    }
}