package com.springboot.forent.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name="amenities")
@Entity
public class Amenities {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_amenity;
	private int id_property;
	private int rooms;
	private int toilets;
	private int beds;
	private String other_amenities;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy="idamenities")
	private Properties property;
	
	
	public Amenities() {
	}
	
	public Amenities(int id_property, int rooms, int toilets, int beds, String other_amenities) {
		this.id_property = id_property;
		this.rooms = rooms;
		this.toilets = toilets;
		this.beds = beds;
		this.other_amenities = other_amenities;
	}

	public Amenities(int rooms, int toilets, int beds, String other_amenities) {
		this.rooms = rooms;
		this.toilets = toilets;
		this.beds = beds;
		this.other_amenities = other_amenities;
	}

	public int getId_amenities() {
		return id_amenity;
	}

	public void setId_amenities(int id_amenity) {
		this.id_amenity = id_amenity;
	}

	public int getId_property() {
		return id_property;
	}

	public void setId_property(int id_property) {
		this.id_property = id_property;
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
