package com.springboot.forent.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.forent.model.Amenities;
import com.springboot.forent.model.Location;
import com.springboot.forent.model.Properties;
import com.springboot.forent.service.PropertiesService;

@RestController
public class PropertiesController {
	@Autowired
    private PropertiesService propertiesService;
	
	@GetMapping("/properties")
    public List<Properties> list() {
        return propertiesService.listAllProperties();
    }
	
	@GetMapping("/users/{id_user}/properties")
	public List<Properties> getListOfProperties(@PathVariable Integer id_user) {
		return propertiesService.getUserProperties(id_user);
	}
	
	@GetMapping("/properties/{id}")
	public ResponseEntity<Properties> get(@PathVariable Integer id){
		return propertiesService.getProperty(id);
	}
	
	@GetMapping("/users/{id_user}/properties/{id_property}")
	public ResponseEntity<Properties> getPropertyOfUser(@PathVariable Integer id_user, @PathVariable Integer id_property) {
		return propertiesService.getPropertyOfUser(id_user, id_property);
	}
	
	@PostMapping("/users/{id_user}/properties")
    public ResponseEntity<Properties> add(@PathVariable Integer id_user, @RequestBody @Valid Properties property, Location location, Amenities amenities){
		return propertiesService.addProperty(id_user, property);
    }
	
    @PutMapping("/users/{id_user}/properties/{id_property}")
    public ResponseEntity<Properties> update(@RequestBody Properties property, @PathVariable Integer id_user, @PathVariable Integer id_property) { 
    	return propertiesService.updateProperty(id_user, id_property, property);
    }
    
    @DeleteMapping("/users/{id_user}/properties/{id_property}")
    public ResponseEntity<String> delete(@PathVariable Integer id_user, @PathVariable Integer id_property) {
    	return propertiesService.deleteProperty(id_user, id_property);
    }
}
