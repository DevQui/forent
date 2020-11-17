package com.springboot.forent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.forent.model.Favorites;
import com.springboot.forent.repository.FavoritesRepository;

@Service
public class FavoritesService {
	@Autowired
	private FavoritesRepository favoritesRepository;
	
	public List<Favorites> listAllFavorites(Integer id_user){
		 //return (List<Favorites>) favoritesRepository.findAll();
		return (List<Favorites>) favoritesRepository.findAllFavorites(id_user);
	}
		
	public Favorites saveFavorite(Favorites favorites) {
		return favoritesRepository.save(favorites);
    }

    public Favorites getFavorite(Integer id_user, Integer id) {
        return favoritesRepository.findUserFavoriteProperty(id_user, id);
    }

    public String deleteFavorite(Integer id_user, Integer id) {
    	favoritesRepository.deleteFavoriteProperty(id_user, id);
    	return "Favorite Property Deleted";
    }
}
