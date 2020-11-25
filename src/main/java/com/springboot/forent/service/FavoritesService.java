package com.springboot.forent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.forent.exception.DataNotFoundException;
import com.springboot.forent.exception.NoDataFoundException;
import com.springboot.forent.model.Favorites;
import com.springboot.forent.repository.FavoritesRepository;

@Service
public class FavoritesService {
	@Autowired
	private FavoritesRepository favoritesRepository;
	
	public List<Favorites> listAllUserFavorites(Integer id_user){
		List<Favorites> favorites = favoritesRepository.listAllUserFavorites(id_user);
		if(!favorites.isEmpty()) {
			return favorites;
		}else {
			throw new NoDataFoundException();
		}
	}
		
	public ResponseEntity<String> savePropertyToFavorites(Integer id_user, Integer id_property) {
		Integer saveFavoriteStatus = favoritesRepository.savePropertyToFavorites(id_user, id_property);
		if (saveFavoriteStatus > 0) {
			return new ResponseEntity<String>("Successfully Added Property to Favorites", HttpStatus.CREATED);
		}else {
			throw new DataNotFoundException(id_property);
		}
    }

    public Favorites getUserFavoriteProperty(Integer id_user, Integer id_favorite) {
        Favorites fave = favoritesRepository.findUserFavoriteProperty(id_user, id_favorite);
        if(fave != null) {
        	return fave;
        }else {
        	throw new DataNotFoundException(id_favorite);
        }
    }

    public ResponseEntity<String> deleteFavoriteProperty(Integer id_user, Integer id_favorite) {
    	Integer deleteFavoriteStatus = favoritesRepository.deleteFavoriteProperty(id_user, id_favorite);
    	if(deleteFavoriteStatus > 0) {
    		return new ResponseEntity<String>("Successfully Delete Favorite", HttpStatus.OK);
    	}else {
    		throw new DataNotFoundException(id_favorite);
    	}
    }
}
