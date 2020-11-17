package com.springboot.forent.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="location")
@Entity
public class Location {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_location;
	private int id_property;
	private String town;
	private String city;
	private String region;
	private String country;
	
	public Location() {
	}
	
	public Location(int id_location, int id_property, String town, String city, String region, String country) {
		super();
		this.id_location = id_location;
		this.id_property = id_property;
		this.town = town;
		this.city = city;
		this.region = region;
		this.country = country;
	}
	
	public Location(int id_property, String town, String city, String region, String country) {
		super();
		this.id_property = id_property;
		this.town = town;
		this.city = city;
		this.region = region;
		this.country = country;
	}

	public Location(String town, String city, String region, String country) {
		super();
		this.town = town;
		this.city = city;
		this.region = region;
		this.country = country;
	}

	public int getId_location() {
		return id_location;
	}


	public void setId_location(int id_location) {
		this.id_location = id_location;
	}


	public int getId_property() {
		return id_property;
	}


	public void setId_property(int id_property) {
		this.id_property = id_property;
	}


	public String getTown() {
		return town;
	}


	public void setTown(String town) {
		this.town = town;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getRegion() {
		return region;
	}


	public void setRegion(String region) {
		this.region = region;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}	
}
