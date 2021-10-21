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
	@Query(nativeQuery = true, value ="INSERT INTO schedules(" +
			"id_property, id_user, status, schedule_date_from, schedule_date_to) " + 
		"SELECT  ?1, ?2, 0, ?3, ?4 FROM properties " + 
		"WHERE EXISTS (SELECT id_property FROM properties WHERE id_property = ?1) AND " +
		"EXISTS (SELECT id_user FROM users WHERE id_user = ?2) " +
		"LIMIT 1")
	Integer saveSchedule(Integer id_property, Integer id_user, String date_from, String date_to);
		
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Schedules SET status = ?3 WHERE id_property =?1 AND id_schedule = ?2")
	Integer acceptSchedule(Integer id_property, Integer id_schedule, Integer status);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("DELETE Schedules WHERE id_user = ?1 AND id_schedule = ?2")
	Integer deleteSchedule(Integer id_user, Integer id_schedule);

	@Query("SELECT sched FROM Schedules sched WHERE sched.id_schedule = ?1")
	Schedules getScheduleById(Integer id_schedule);
	
	@Query("SELECT sched FROM Schedules sched WHERE sched.id_property = ?1 AND sched.id_schedule = ?2")
	Schedules getPropertySchedule(Integer id_property, Integer id_schedule);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Schedules SET status = ?4 WHERE id_user = ?1 AND id_property =?2 AND id_schedule = ?3")
	Integer acceptSchedule2(Integer id_user, Integer id_property, Integer id_schedule, int status);
}