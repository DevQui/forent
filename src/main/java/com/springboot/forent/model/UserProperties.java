package com.springboot.forent.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "users")
@Entity
public class UserProperties {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_user;
	private String type;
	private String first_name;
	private String middle_name;
	private String last_name;
	private String email;
	private String phone_number;
	
	public UserProperties() {
	}
	
	
	public UserProperties(int id_user, String type, String first_name, String middle_name, String last_name,
			String email, String phone_number) {
		super();
		this.id_user = id_user;
		this.type = type;
		this.first_name = first_name;
		this.middle_name = middle_name;
		this.last_name = last_name;
		this.email = email;
		this.phone_number = phone_number;
	}


	public int getId_user() {
		return id_user;
	}
	
	public String getType() {
		return type;
	}
	
	public String getFirst_name() {
		return first_name;
	}
	
	public String getMiddle_name() {
		return middle_name;
	}
	
	public String getLast_name() {
		return last_name;
	}
	
	public String getEmail() {
		return email;
	}

	public String getPhone_number() {
		return phone_number;
	}
}
