package com.springboot.forent.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name="properties")
@Entity
public class Properties {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_property;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_user")
	private Users iduser;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_location")
	private Location idlocation;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_amenities")
	private Amenities idamenities;
	private String type;
	private String name;
	private String description;
	private Float price;
	private String created_datetime;
	private String updated_datetime;

	/*@OneToOne(cascade=CascadeType.ALL, mappedBy="idproperty")
	private Users user;*/
	
	public Properties() {
	}
	
	//constructor for post
	public Properties(Users id_user, Location id_location, Amenities id_amenities, String type, String name, String description,
			Float price, String created_datetime) {
		
		this.iduser = id_user;
		this.idlocation = id_location;
		this.idamenities = id_amenities;
		this.type = type;
		this.name = name;
		this.description = description;
		this.price = price;
		this.created_datetime = created_datetime;
	}
	
	//constructor for put
	public Properties(String type, String name, String description, Float price,
			String updated_datetime) {
		this.type = type;
		this.name = name;
		this.description = description;
		this.price = price;
		this.updated_datetime = updated_datetime;
	}


	public Properties(Properties property, Location location, Amenities amenities, Users user) {
		this.id_property = property.getId_property();
		this.iduser = user;
		this.idlocation = location;
		this.idamenities = amenities;
		this.type = property.getType();
		this.name = property.getName();
		this.description = property.getDescription();
		this.price = property.getPrice();
		this.created_datetime = property.getCreated_datetime();
		this.updated_datetime = property.getUpdated_datetime();
	}


	public Properties(Properties property) {
		this.id_property = property.getId_property();
		this.type = property.getType();
		this.name = property.getName();
		this.description = property.getDescription();
		this.price = property.getPrice();
		this.created_datetime = property.getCreated_datetime();
		this.updated_datetime = property.getUpdated_datetime();
	}

	public int getId_property() {
		return id_property;
	}


	public void setId_property(int id_property) {
		this.id_property = id_property;
	}


	public Users getId_user() {
		return iduser;
	}


	public void setId_user(Users id_user) {
		this.iduser = id_user;
	}


	public Location getId_location() {
		return idlocation;
	}


	public void setId_location(Location id_location) {
		this.idlocation = id_location;
	}


	public Amenities getId_amenities() {
		return idamenities;
	}


	public void setId_amenities(Amenities id_amenities) {
		this.idamenities = id_amenities;
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
