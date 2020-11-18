package com.springboot.forent.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name="users")
@Entity
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //to generation strategies for the values of primary keys.
	private int id_user;
	private String type;
	private String first_name;
	private String middle_name;
	private String last_name;
	private String email;
	private String phone_number;
	private String user_password;
	private String created_datetime;
	private String updated_datetime;
	
	@OneToMany(targetEntity = Favorites.class, cascade=CascadeType.ALL)
	@JoinColumn(name = "id_user")
	private List<Favorites> favorites;
	
	@OneToMany(targetEntity = Properties.class, cascade=CascadeType.ALL)
	@JoinColumn(name = "users_id_user")
	private List<Properties> properties;
	
	public Users() {
	}

	public Users(int id_user, String type, String first_name, String middle_name, String last_name, String email, String phone_number,
			String user_password, String created_datetime) {
		this.id_user = id_user;
		this.type = type;
		this.first_name = first_name;
		this.middle_name = middle_name;
		this.last_name = last_name;
		this.email = email;
		this.phone_number = phone_number;
		this.user_password = user_password;
		this.created_datetime = created_datetime;
	}

	//setters and getters
	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getMiddle_name() {
		return middle_name;
	}

	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone_number() {
		return phone_number;
	}
	
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getCreated_datetime() {
		return created_datetime;
	}

	public void setCreated_datetime(String created_datetime) {
		this.created_datetime = created_datetime;
	}

	public String getUpdated_datetime() {
		return updated_datetime;
	}

	public void setUpdated_datetime(String updated_datetime) {
		this.updated_datetime = updated_datetime;
	}

	public List<Favorites> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<Favorites> favorites) {
		this.favorites = favorites;
	}

	public List<Properties> getProperties() {
		return properties;
	}

	public void setProperties(List<Properties> properties) {
		this.properties = properties;
	}
}
