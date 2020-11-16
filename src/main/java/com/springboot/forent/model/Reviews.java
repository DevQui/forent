package com.springboot.forent.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="reviews")
@Entity
public class Reviews {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //to generation strategies for the values of primary keys.
	private int id_review;
	private int id_user;
	private int rating;
	private String comment;
	private String created_datetime;
	private int id_property;
	
	@ManyToOne
	public Properties properties;
	
	public Reviews() {
	}
	
	public Reviews(int id_review, int id_user, int rating, String comment, String created_datetime, int id_property) {
		super();
		this.id_review = id_review;
		this.id_user = id_user;
		this.rating = rating;
		this.comment = comment;
		this.created_datetime = created_datetime;
		this.id_property = id_property;
	}

	public Reviews(int id_review, int rating, String comment, String created_datetime, int id_property) {
		this.id_review = id_review;
		this.rating = rating;
		this.comment = comment;
		this.created_datetime = created_datetime;
		this.id_property = id_property;
	}
	

	public int getId_review() {
		return id_review;
	}

	public void setId_review(int id_review) {
		this.id_review = id_review;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCreated_datetime() {
		return created_datetime;
	}

	public void setCreated_datetime(String created_datetime) {
		this.created_datetime = created_datetime;
	}

	public int getId_property() {
		return id_property;
	}

	public void setId_property(int id_property) {
		this.id_property = id_property;
	}
}
