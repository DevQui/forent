package com.springboot.forent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.forent.model.Favorites;
import com.springboot.forent.service.FavoritesService;

@RestController
public class FavoritesController {
	@Autowired
    private FavoritesService favoritesService;
	
	@GetMapping("/favorites")
    public List<Favorites> listAllFavoriteProperties(){
        return favoritesService.listAllUserFavoriteProperties();
    }
	
	@GetMapping("/favorites/{id_favorite}")
    public Favorites getSpecificFavoriteProperty(@PathVariable Integer id_favorite){
        return favoritesService.getSpecificFavoriteProperty(id_favorite);
    }
	
	@GetMapping("/users/{id_user}/favorites")
    public List<Favorites> listAllUserFavorites(@PathVariable Integer id_user) {
        return favoritesService.listAllUserFavorites(id_user);
    }
	
	@GetMapping("/users/{id_user}/favorites/{id_favorite}")
    public Favorites getUserFavoriteProperty(@PathVariable Integer id_user, @PathVariable Integer id_favorite) {
        return favoritesService.getUserFavoriteProperty(id_user, id_favorite);
    }
	
	@PostMapping("/users/{id_user}/properties/{id_property}/favorites")
    public ResponseEntity<String> add(@PathVariable Integer id_user, @PathVariable Integer id_property) {
		return favoritesService.savePropertyToFavorites(id_user, id_property);
    }
    
    @DeleteMapping("/users/{id_user}/favorites/{id_favorite}")
    public ResponseEntity<String> delete(@PathVariable Integer id_user, @PathVariable Integer id_favorite) {
    	return favoritesService.deleteFavoriteProperty(id_user, id_favorite);
    }
}
