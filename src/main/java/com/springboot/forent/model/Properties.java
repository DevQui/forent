package com.springboot.forent.model;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name="properties")
@Entity
public class Properties {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_property;
	private String type;
	private String name;
	private String description;
	private Float price;
	private String created_datetime;
	private String updated_datetime;

	@OneToOne
	private Users user;
	
	@OneToOne
	private Location location;
	
	@OneToOne
	private Amenities amenities;
	
	@OneToMany
	private List<Reviews> reviews;
	
	
	public Properties() {
	}

	public Properties(int id_property, Users id_user, Location location, Amenities amenities, String type, String name,
			String description, Float price, String created_datetime, String updated_datetime) {
		super();
		this.id_property = id_property;
		this.user = id_user;
		this.location = location;
		this.amenities = amenities;
		this.type = type;
		this.name = name;
		this.description = description;
		this.price = price;
		this.created_datetime = created_datetime;
		this.updated_datetime = updated_datetime;
	}

	public Properties(String type, String name, String description, Float price,
			String created_datetime) {
		super();
		this.type = type;
		this.name = name;
		this.description = description;
		this.price = price;
		this.created_datetime = created_datetime;
	}

	public Properties(int id_property, String type, String name, String description, Float price,
			String updated_datetime) {
		super();
		this.id_property = id_property;
		this.type = type;
		this.name = name;
		this.description = description;
		this.price = price;
		this.updated_datetime = updated_datetime;
	}

	public int getId_property() {
		return id_property;
	}

	public void setId_property(int id_property) {
		this.id_property = id_property;
	}

	public Users getId_user() {
		return user;
	}

	public void setId_user(Users id_user) {
		this.user = id_user;
	}
	
	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Location getLocation() {
		return location;
	}	

	public void setLocation(Location location) {
		this.location = location;
	}

	public Amenities getAmenities() {
		return amenities;
	}

	public void setAmenities(Amenities amenities) {
		this.amenities = amenities;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
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
}
