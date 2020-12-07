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

	public int getId_favorite() {
		return id_favorite;
	}

	public int getId_property() {
		return id_property;
	}
}
