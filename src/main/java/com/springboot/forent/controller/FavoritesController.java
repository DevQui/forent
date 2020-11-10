package com.springboot.forent.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.springboot.forent.model.Favorites;
import com.springboot.forent.model.Users;
import com.springboot.forent.service.FavoritesService;

@RestController
public class FavoritesController {
	@Autowired
    private FavoritesService favoritesService;
	
	@GetMapping("/favorites")
    public List<Favorites> list() {
        return favoritesService.listAllFavorites();
    }
	
	
	/*@GetMapping("/favorites/{id}")
    public ResponseEntity<Favorites> get(@PathVariable Integer id) {
        try {
        	Favorites favorite = favoritesService.getFavorite(id);
            return new ResponseEntity<Favorites>(favorite, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Favorites>(HttpStatus.NOT_FOUND);
        }
	}*/
	
	@GetMapping("/favorites/{id}")
	public Favorites get(@PathVariable Integer id){
        try {
        	RestTemplate restTemplate = new RestTemplate();        	
            Favorites favorite = favoritesService.getFavorite(id);
            Users user = restTemplate.getForObject("http://localhost:8080/users/"+favorite.getId_user().getId_user(), Users.class);
            return new Favorites(favorite, user);
            //return new ResponseEntity<Properties>(property, HttpStatus.OK);
        } catch (NoSuchElementException e) {;
            //return new ResponseEntity<Properties>(HttpStatus.NOT_FOUND);
        	return null;	
        }
	}
	
	@PostMapping("/favorites")
    public void add(@RequestBody Favorites favorites) {
		favoritesService.saveFavorite(favorites);
    }
	
    
    @DeleteMapping("/favorites/{id}")
    public void delete(@PathVariable Integer id) {
    	favoritesService.deleteFavorite(id);
    }
}
