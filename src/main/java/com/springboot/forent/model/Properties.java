package com.springboot.forent.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;



@Table(name="properties")
@Entity
public class Properties {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_property;
	@NotBlank(message = "Type should not be null or blank")
	private String type;
	@NotBlank(message = "Name should not be null or blank")
	private String name;
	@NotBlank(message = "Description should not be null or blank")
	private String description;
	private Float price;
	private String created_datetime;
	private String updated_datetime;
	private int users_id_user;
	
	@ManyToOne(targetEntity = UserProperties.class, cascade=CascadeType.ALL)
	@JoinColumn(name = "users_id_user",  insertable = false, updatable = false)
	private UserProperties users;
	
	public int getUsers_id_user() {
		return users_id_user;
	}

	public void setUsers_id_user(int users_id_user) {
		this.users_id_user = users_id_user;
	}

	@OneToOne(cascade=CascadeType.ALL)
	private Location location;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Amenities amenities;
	
	@OneToMany(targetEntity = Reviews.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_property")
	private List<Reviews> reviews;
	
	
	public Properties() {
	}

	public Properties(int id_property, UserProperties users, Location location, Amenities amenities, 
			List<Reviews> reviews, String type, String name, String description, Float price, 
			String created_datetime, String updated_datetime) {
		super();
		this.id_property = id_property;
		this.users = users;
		this.location = location;
		this.amenities = amenities;
		this.reviews = reviews;
		this.type = type;
		this.name = name;
		this.description = description;
		this.price = price;
		this.created_datetime = created_datetime;
		this.updated_datetime = updated_datetime;
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

	public Properties(int id_property, int users_id_user, String type, String name, String description, Float price,
			String created_datetime, String updated_datetime) {
		super();
		this.id_property = id_property;
		this.users_id_user = users_id_user;
		this.type = type;
		this.name = name;
		this.description = description;
		this.price = price;
		this.created_datetime = created_datetime;
		this.updated_datetime = updated_datetime;
		
	}

	public int getId_property() {
		return id_property;
	}

	public void setId_property(int id_property) {
		this.id_property = id_property;
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

	public UserProperties getUsers() {
		return users;
	}

	public void setUsers(UserProperties users) {
		this.users = users;
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

	public List<Reviews> getReviews() {
		return reviews;
	}

	public void setReviews(List<Reviews> reviews) {
		this.reviews = reviews;
	}
}
