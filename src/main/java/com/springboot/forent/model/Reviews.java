package com.springboot.forent.model;

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
	private int rating;
	private String comment;
	private String created_datetime;
	private int id_property;
	
	public Reviews() {
	}
	
	public Reviews(int rating, String comment, String created_datetime, int id_property) {
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
