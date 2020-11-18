package com.springboot.forent.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="favorites")
@Entity
public class Favorites {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_favorite;
	private int id_property;
	private int id_user;

	
	public Favorites() {
	}
	
	public Favorites(int id_favorite, int id_property, int id_user) {
		this.id_favorite = id_favorite;
		this.id_property = id_property;
		this.id_user = id_user;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public int getId_favorite() {
		return id_favorite;
	}

	public void setId_favorite(int id_favorite) {
		this.id_favorite = id_favorite;
	}

	public int getId_property() {
		return id_property;
	}

	public void setId_property(int id_property) {
		this.id_property = id_property;
	}
}
