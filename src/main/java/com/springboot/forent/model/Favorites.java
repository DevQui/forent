package com.springboot.forent.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name="favorites")
@Entity
public class Favorites {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_favorite;
	private int id_property;
	private int id_user;
	/*@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_user")
	private Users iduser;*/
	
	public Favorites() {
	}
	
	/*public Favorites(int id_property, Users id_user) {
		this.id_property = id_property;
		this.iduser = id_user;
	}*/
	

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

	public Favorites(int id_property, int id_user) {
		super();
		this.id_property = id_property;
		this.id_user = id_user;
	}

	public Favorites(Favorites favorite, Users user) {
		// TODO Auto-generated constructor stub
		this.id_favorite = favorite.getId_favorite();
		this.id_property = favorite.getId_property();
		//this.iduser = user;
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

	/*public Users getId_user() {
		return iduser;
	}

	public void setId_user(Users id_user) {
		this.iduser = id_user;
	}*/
}
