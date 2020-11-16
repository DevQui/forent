package com.springboot.forent.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.forent.model.Location;
import com.springboot.forent.service.LocationService;

@RestController
public class LocationController {
	@Autowired
    private LocationService locationService;

	
	@GetMapping("/location")
    public List<Location> list() {
        return locationService.listAllLocation();
    }
	
	@GetMapping("properties/{id_property}/location/{id}")
    public ResponseEntity<Location> get(@PathVariable Integer id_property, @PathVariable Integer id) {
        try {
        	Location location = locationService.getLocation(id);
            return new ResponseEntity<Location>(location, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Location>(HttpStatus.NOT_FOUND);
        }
	}	
	
	
	@PostMapping("/properties/{id_property}/location")
	public ResponseEntity<Location> add(@RequestBody Location location, @PathVariable Integer id_property) {
		try {
			location.setId_property(id_property);
			Location response = locationService.saveLocation(location);
			return new ResponseEntity<Location>(response,HttpStatus.CREATED);
		}catch(Exception ex) {
			return new ResponseEntity<>(location,HttpStatus.PRECONDITION_REQUIRED);
		}
    }
	
	@PatchMapping("/properties/{id_property}/location/{id}")
	public ResponseEntity<Location> update(@RequestBody Location location, @PathVariable Integer id, @PathVariable Integer id_property) {
        try {
        	Location existLocation= locationService.getLocation(id);
        	
        	if(existLocation != null) {
        		try{
        			existLocation.setId_location(existLocation.getId_location());
        			if(location.getTown() != null && !location.getTown().isEmpty()) { 
        				existLocation.setTown(location.getTown());
        			}
        			
        			if(location.getCity() != null && !location.getCity().isEmpty()) {
        				existLocation.setCity(location.getCity());
        			}
        				
    				locationService.saveLocation(existLocation);
        			
        		
        		}catch(NullPointerException ex) {
        			return new ResponseEntity<>(HttpStatus.PRECONDITION_REQUIRED);
        		}
        	}
            return new ResponseEntity<Location>(location, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
