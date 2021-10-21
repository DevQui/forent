package com.springboot.forent.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="schedules")
@Entity
public class Schedules {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_schedule;
	private int id_property;
	private int id_user;
	private int status;
	private String schedule_date_from;
	private String schedule_date_to;
	
	public Schedules() {	
	}
	
	public Schedules(int id_schedule, int id_property, int id_user, int status, String schedule_date_from,
			String schedule_date_to) {
		super();
		this.id_schedule = id_schedule;
		this.id_property = id_property;
		this.id_user = id_user;
		this.status = status;
		this.schedule_date_from = schedule_date_from;
		this.schedule_date_to = schedule_date_to;
	}


	public int getId_schedule() {
		return id_schedule;
	}

	public void setId_schedule(int id_schedule) {
		this.id_schedule = id_schedule;
	}

	public int getId_property() {
		return id_property;
	}

	public void setId_property(int id_property) {
		this.id_property = id_property;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getSchedule_date_from() {
		return schedule_date_from;
	}
	
	public void setSchedule_date_from(String schedule_date_from) {
		this.schedule_date_from = schedule_date_from;
	}
	
	public String getSchedule_date_to() {
		return schedule_date_to;
	}
	
	public void setSchedule_date_to(String schedule_date_to) {
		this.schedule_date_to = schedule_date_to;
	}
}
