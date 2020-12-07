package com.springboot.forent.model;

import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;	

@Table(name="reviews")
@Entity
public class Reviews {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //to generation strategies for the values of primary keys.
	private int id_review;
	private int id_property;
	private int id_user;
	private int rating;
	private String comment;
	private OffsetDateTime created_datetime;
	
	public Reviews() {
	}

	public Reviews(int idReview, int idProperty, int id_user, int rating, String comment, OffsetDateTime created_datetime) {
		super();
		this.id_review = idReview;
		this.id_property = idProperty;
		this.id_user = id_user;
		this.rating = rating;
		this.comment = comment;
		this.created_datetime = created_datetime;
	}

	public Reviews(int idProperty, int id_user, int rating, String comment, OffsetDateTime created_datetime) {
		super();
		this.id_property = idProperty;
		this.id_user = id_user;
		this.rating = rating;
		this.comment = comment;
		this.created_datetime = created_datetime;
	}
	

	public int getIdReview() {
		return id_review;
	}

	public void setIdReview(int idReview) {
		this.id_review = idReview;
	}

	public int getIdProperty() {
		return id_property;
	}

	public void setIdProperty(int idProperty) {
		this.id_property = idProperty;
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

	public OffsetDateTime getCreated_datetime() {
		return created_datetime;
	}

	public void setCreated_datetime(OffsetDateTime created_datetime) {
		this.created_datetime = created_datetime;
	}
}
