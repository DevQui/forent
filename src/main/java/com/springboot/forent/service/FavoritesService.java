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
	
	public List<Favorites> listAllFavorites(){
		 return (List<Favorites>) favoritesRepository.findAll();
	}
	
	public Favorites saveFavorite(Favorites favorites) {
		return favoritesRepository.save(favorites);
    }

    public Favorites getFavorite(Integer id) {
        return favoritesRepository.findById(id).get();
    }

    public String deleteFavorite(Integer id) {
    	favoritesRepository.deleteById(id);
    	return "Favorite Property Deleted";
    }
}
