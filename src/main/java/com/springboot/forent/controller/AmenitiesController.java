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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.forent.model.Amenities;
import com.springboot.forent.service.AmenitiesService;
import com.springboot.forent.service.PropertiesService;

@RestController
public class AmenitiesController {
	@Autowired
    private AmenitiesService amenitiesService;
	
	@Autowired
	private PropertiesService propertiesService;
	
	@GetMapping("/amenities")
    public List<Amenities> list() {
        return amenitiesService.listAllAmenities();
    }
	
	//@GetMapping("properties/{id}/amenities")
	@GetMapping("amenities/{id}")
    public ResponseEntity<Amenities> get(@PathVariable Integer id) {
        try {
        	
            Amenities amenisties = amenitiesService.getAmenities(id);
            return new ResponseEntity<Amenities>(amenisties, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Amenities>(HttpStatus.NOT_FOUND);
        }
	}	
	
	@PostMapping("/amenities")
	public void add(@RequestBody Amenities amenities) {
        amenitiesService.saveAmenities(amenities);
    }	
	
	@PatchMapping("/amenities/{id}")
	public ResponseEntity<Amenities> update(@RequestBody Amenities amenities, @PathVariable Integer id) {
        try {
        	Amenities existAmenities = amenitiesService.getAmenities(id);
        	
        	if(existAmenities != null) {
        		try{
        			
        			if(amenities.getOther_amenities() != null || !amenities.getOther_amenities().isEmpty()) {
        				existAmenities.setRooms(existAmenities.getRooms());
        				existAmenities.setToilets(existAmenities.getToilets());
        				existAmenities.setBeds(existAmenities.getBeds());
        				existAmenities.setOther_amenities(existAmenities.getOther_amenities());
            	    	amenitiesService.saveAmenities(amenities);
        			}
        		
        		}catch(NullPointerException ex) {
        			return new ResponseEntity<>(HttpStatus.PRECONDITION_REQUIRED);
        		}
        	}
            return new ResponseEntity<Amenities>(amenities, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	@DeleteMapping("/amenities/{id}")
	public void delete(@PathVariable Integer id) {
		amenitiesService.deleteAmenities(id);
	}
}
