package com.springboot.forent.controller;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

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
	
	@GetMapping("/users/{id}/favorites")
	public List<Favorites> getFavorites(@PathVariable Integer id) {
		return favoritesService.getUsersFavorites(id);
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
	public ResponseEntity<Favorites> get(@PathVariable Integer id){
        try {
        	RestTemplate restTemplate = new RestTemplate();        	
            Favorites favorite = favoritesService.getFavorite(id);
            //Users user = restTemplate.getForObject("http://localhost:8080/users/"+favorite.getId_user(), Users.class);
            //return new Favorites(favorite, user);
            
            return new ResponseEntity<Favorites>(favorite, HttpStatus.OK);
        } catch (NoSuchElementException e) {;
            return new ResponseEntity<Favorites>(HttpStatus.NOT_FOUND);
        	
        }
	}
	
	@PostMapping("/favorites")
    public ResponseEntity<Favorites> add(@RequestBody Favorites favorites) {
		try {
			HttpHeaders header = new HttpHeaders();
			header.setLocation(new URI("/favorites"));
			Favorites response = favoritesService.saveFavorite(favorites);
			return new ResponseEntity<Favorites>(response,header,HttpStatus.CREATED);
		}catch(Exception ex) {
			return new ResponseEntity<>(favorites,HttpStatus.PRECONDITION_REQUIRED);
		}
    }
	
    
    @DeleteMapping("/favorites/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
    	String response = favoritesService.deleteFavorite(id);
    	return new ResponseEntity<String>(response,HttpStatus.OK);
    }
}
