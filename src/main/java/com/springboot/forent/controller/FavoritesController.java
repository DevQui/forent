package com.springboot.forent.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.forent.model.Favorites;
import com.springboot.forent.service.FavoritesService;

@RestController
public class FavoritesController {
	@Autowired
    private FavoritesService favoritesService;
	
	@GetMapping("/users/{id_user}/favorites")
    public List<Favorites> list(@PathVariable Integer id_user) {
        return favoritesService.listAllFavorites(id_user);
    }
	
	@GetMapping("/users/{id_user}/favorites/{id}")
    public Favorites list(@PathVariable Integer id_user, @PathVariable Integer id) {
        return favoritesService.getFavorite(id_user, id);
    }
	
	@PostMapping("/users/{id_user}/favorites")
    public ResponseEntity<Favorites> add(@PathVariable Integer id_user, @RequestBody Favorites favorites) {
		try {
			HttpHeaders header = new HttpHeaders();
			header.setLocation(new URI("/favorites"));
			favorites.setId_user(id_user);
			Favorites response = favoritesService.saveFavorite(favorites);
			return new ResponseEntity<Favorites>(response,header,HttpStatus.CREATED);
		}catch(Exception ex) {
			return new ResponseEntity<>(favorites,HttpStatus.PRECONDITION_REQUIRED);
		}
    }
    
    @DeleteMapping("/users/{id_user}/favorites/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id_user, @PathVariable Integer id) {
    	String response = favoritesService.deleteFavorite(id_user, id);
    	return new ResponseEntity<String>(response,HttpStatus.OK);
    }
}
