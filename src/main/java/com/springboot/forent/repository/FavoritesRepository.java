package com.springboot.forent.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.forent.model.Favorites;

@Repository
public interface FavoritesRepository extends CrudRepository<Favorites, Integer>{
	//public List<Favorites>findByIduser(Integer iduser);

}
