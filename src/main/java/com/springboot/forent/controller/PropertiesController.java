package com.springboot.forent.controller;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	
	@GetMapping("/properties/{id}")
	public ResponseEntity<Properties> get(@PathVariable Integer id){
		try {
            Properties property = propertiesService.getProperty(id);
            return new ResponseEntity<Properties>(property, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Properties>(HttpStatus.NOT_FOUND);
        }
	}
	
	@PostMapping("/users/{id_user}/properties")
    public ResponseEntity<Properties> add(@PathVariable Integer id_user, @RequestBody Properties property, Location location, Amenities amenities) {
		try {
			OffsetDateTime current = OffsetDateTime.now();
			String created_datetime = current.toString();	
			property.setCreated_datetime(created_datetime);
			property.setUsers_id_user(id_user);
			Properties propertySaved =  propertiesService.saveProperty(property);
			return new ResponseEntity<Properties>(propertySaved, HttpStatus.CREATED);
		}catch( Exception ex) {
			return new ResponseEntity<Properties>(HttpStatus.BAD_REQUEST);
		}
    }
	
	@GetMapping("/users/{id_user}/properties")
	public List<Properties> getListOfProperties(@PathVariable Integer id_user) {
		return propertiesService.getUserProperties(id_user);
	}
	
    @PutMapping("/users/{id_user}/properties/{id_property}")
    public ResponseEntity<Properties> update(@RequestBody Properties property, @PathVariable Integer id_user, @PathVariable Integer id_property) {
        try {
        	Properties existProperty = propertiesService.getProperty(id_property);
        	
        	if(existProperty != null) {
            	OffsetDateTime current = OffsetDateTime.now();
        		String updated_datetime = current.toString();
        		
        		try{
        			if(property.getType() != null && !property.getType().isBlank()) { 
    					existProperty.setType(property.getType());	
        			}
        			if(property.getName() != null) {
    					existProperty.setName(property.getName());	
        			}
        			if(property.getPrice() != null) {
        				existProperty.setPrice(property.getPrice());
        			}
        			if(property.getDescription() != null && !property.getDescription().isEmpty()) {
        				existProperty.setDescription(property.getDescription());
        			}
    				existProperty.setUpdated_datetime(updated_datetime);
        	    	propertiesService.saveProperty(existProperty);
        		
        		}catch(NullPointerException ex) {
        			return new ResponseEntity<>(HttpStatus.PRECONDITION_REQUIRED);
        		}
        	}
            return new ResponseEntity<Properties>(existProperty, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/users/{id_user}/properties/{id_property}")
    public ResponseEntity<String> delete(@PathVariable Integer id_user, @PathVariable Integer id_property) {
    	return propertiesService.deleteProperty(id_user, id_property);
    }
}
