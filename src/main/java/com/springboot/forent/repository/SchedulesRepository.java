package com.springboot.forent.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.forent.model.Schedules;

public interface SchedulesRepository extends CrudRepository<Schedules, Integer>{

}