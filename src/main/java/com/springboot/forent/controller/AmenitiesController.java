package com.springboot.forent.controller;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.forent.model.Amenities;
import com.springboot.forent.service.AmenitiesService;

@RestController
public class AmenitiesController {
	@Autowired
    private AmenitiesService amenitiesService;
	
	@GetMapping("properties/{id_property}/amenities")
    public List<Amenities> list(@PathVariable Integer id_property) {
        return amenitiesService.listPropertyAmenities(id_property);
    }
	
	@GetMapping("/properties/{id_property}/amenities/{id}")
    public ResponseEntity<Amenities> get(@PathVariable Integer id_property, @PathVariable Integer id) {
        try {
        	
            Amenities amenisties = amenitiesService.getAmenities(id);
            return new ResponseEntity<Amenities>(amenisties, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Amenities>(HttpStatus.NOT_FOUND);
        }
	}	
	
	@PostMapping("/properties/{id_property}/amenities")
	public ResponseEntity<Amenities>  add(@RequestBody Amenities amenities, @PathVariable Integer id_property) {
        try {
			HttpHeaders header = new HttpHeaders();
			header.setLocation(new URI("/amenities"));
			amenities.setId_property(id_property);
			Amenities response = amenitiesService.saveAmenities(amenities);
			return new ResponseEntity<Amenities>(response,header,HttpStatus.CREATED);
		}catch(Exception ex) {
			return new ResponseEntity<>(HttpStatus.PRECONDITION_REQUIRED);
		}
        
    }	
	
	@PatchMapping("/properties/{id_property}/amenities/{id}")
	public ResponseEntity<Amenities> update(@RequestBody Amenities amenities, @PathVariable Integer id_property, @PathVariable Integer id) {
        try {
        	Amenities existAmenities = amenitiesService.getAmenities(id);
        	
        	if(existAmenities != null) {
        		
				existAmenities.setRooms(amenities.getRooms());
				existAmenities.setToilets(amenities.getToilets());
				existAmenities.setBeds(amenities.getBeds());    			
    			if(amenities.getOther_amenities() != null && !amenities.getOther_amenities().isEmpty()) {
    				existAmenities.setOther_amenities(amenities.getOther_amenities());
    			}
    			amenitiesService.saveAmenities(existAmenities);
			
        	}
            return new ResponseEntity<Amenities>(amenities, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
