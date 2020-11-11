package com.springboot.forent.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="amenities")
@Entity
public class Amenities {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_amenity;
	private int idProperty;
	private int rooms;
	private int toilets;
	private int beds;
	private String other_amenities;
	
	public Amenities() {
	}

	public Amenities(int id_amenity, int id_property, int rooms, int toilets, int beds, String other_amenities) {
		super();
		this.id_amenity = id_amenity;
		this.idProperty = id_property;
		this.rooms = rooms;
		this.toilets = toilets;
		this.beds = beds;
		this.other_amenities = other_amenities;
	}

	public Amenities(int id_property, int rooms, int toilets, int beds, String other_amenities) {
		super();
		this.idProperty = id_property;
		this.rooms = rooms;
		this.toilets = toilets;
		this.beds = beds;
		this.other_amenities = other_amenities;
	}

	public Amenities(int rooms, int toilets, int beds, String other_amenities) {
		super();
		this.rooms = rooms;
		this.toilets = toilets;
		this.beds = beds;
		this.other_amenities = other_amenities;
	}

	public int getId_amenity() {
		return id_amenity;
	}

	public void setId_amenity(int id_amenity) {
		this.id_amenity = id_amenity;
	}

	public int getId_property() {
		return idProperty;
	}

	public void setId_property(int id_property) {
		this.idProperty = id_property;
	}

	public int getRooms() {
		return rooms;
	}

	public void setRooms(int rooms) {
		this.rooms = rooms;
	}

	public int getToilets() {
		return toilets;
	}

	public void setToilets(int toilets) {
		this.toilets = toilets;
	}

	public int getBeds() {
		return beds;
	}

	public void setBeds(int beds) {
		this.beds = beds;
	}

	public String getOther_amenities() {
		return other_amenities;
	}

	public void setOther_amenities(String other_amenities) {
		this.other_amenities = other_amenities;
	}
}
