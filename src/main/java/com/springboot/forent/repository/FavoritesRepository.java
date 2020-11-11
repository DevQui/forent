package com.springboot.forent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.forent.model.Favorites;

@Repository
public interface FavoritesRepository extends CrudRepository<Favorites, Integer>{
	//public List<Favorites>findByIduser(Integer iduser);
	@Query("SELECT fave FROM Users AS u, Favorites AS fave " + 
			"WHERE fave.idUser = u.id_user " +
			"AND fave.idUser = :id")
	public List<Favorites> findByFavoritesIdUser(@Param("id") Integer id);
}
