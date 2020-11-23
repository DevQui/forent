package com.springboot.forent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.forent.model.Schedules;

@Repository
public interface SchedulesRepository extends CrudRepository<Schedules, Integer>{

	@Query("SELECT sched FROM Schedules sched WHERE sched.id_property = ?1")
	List<Schedules> findPropertySchedules(Integer id_property);
	
	@Query("SELECT sched FROM Schedules sched WHERE sched.id_user = ?1")
	List<Schedules> findUserSchedules(Integer id_user);

	@Query("SELECT sched FROM Schedules sched WHERE sched.id_user = ?1 AND sched.id_schedule = ?2")
	Schedules getUserScheduleDetails(Integer id_user, Integer id_schedule);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("INSERT INTO Schedules(" +
			"id_property, id_user, status, schedule_date_from, schedule_date_to) " + 
		"SELECT  p.id_property, p.users_id_user, 0, ?3, ?4 FROM Properties p " + 
		"WHERE p.users_id_user = ?1 AND p.id_property = ?2 ")
	Integer saveSchedule(Integer id_user, Integer id_property, String date_from, String date_to);
		
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Schedules SET status = 1 WHERE id_user =?1 AND id_property = ?2 AND id_schedule = ?3")
	Integer acceptSchedule(Integer id_user, Integer id_property, Integer id_schedule, Integer status);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("DELETE Schedules WHERE id_user = ?1 AND id_property = ?2 AND id_schedule = ?3")
	Integer deleteSchedule(Integer id_user, Integer id_property, Integer id_schedule);
}