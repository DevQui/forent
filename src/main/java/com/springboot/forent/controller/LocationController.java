package com.springboot.forent.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	
	@GetMapping("/location/{id}")
    public ResponseEntity<Location> get(@PathVariable Integer id) {
        try {
        	Location location = locationService.getLocation(id);
            return new ResponseEntity<Location>(location, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Location>(HttpStatus.NOT_FOUND);
        }
	}	
	
	
	@PostMapping("/location")
	public void add(@RequestBody Location location) {
		locationService.saveLocation(location);
    }	
	
	@PatchMapping("/location/{id}")
	public ResponseEntity<Location> update(@RequestBody Location location, @PathVariable Integer id) {
        try {
        	Location existLocation= locationService.getLocation(id);
        	
        	if(existLocation != null) {
        		try{
        			
        			if(location.getTown() != null || !location.getTown().isEmpty() || 
        					location.getCity() != null || !location.getCity().isEmpty()) {
        				existLocation.setTown(location.getTown());
        				existLocation.setCity(location.getCity());
        				if(location.getRegion() != null || !location.getRegion().isEmpty()) {
        					existLocation.setRegion(existLocation.getRegion());
        				}
        				if(location.getCountry() != null || !location.getCountry().isEmpty()) {
        					existLocation.setCountry(existLocation.getCountry());
        				}
        				
        				locationService.saveLocation(location);
        			}
        		
        		}catch(NullPointerException ex) {
        			return new ResponseEntity<>(HttpStatus.PRECONDITION_REQUIRED);
        		}
        	}
            return new ResponseEntity<Location>(location, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	@DeleteMapping("/location/{id}")
	public void delete(@PathVariable Integer id) {
		locationService.deleteLocation(id);
	}
}
