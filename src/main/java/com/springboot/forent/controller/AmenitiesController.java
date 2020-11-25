package com.springboot.forent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Amenities> listPropertyAmenities(@PathVariable Integer id_property) {
        return amenitiesService.listPropertyAmenities(id_property);
    }
	
	@GetMapping("/properties/{id_property}/amenities/{id_amenity}")
    public Amenities getPropertyAmenities(@PathVariable Integer id_property, @PathVariable Integer id_amenity) {
        return amenitiesService.getPropertyAmenities(id_property, id_amenity);
	}	
	
	@PostMapping("/properties/{id_property}/amenities")
	public ResponseEntity<String> saveAmenities(@RequestBody Amenities amenities, @PathVariable Integer id_property) {
        return amenitiesService.saveAmenities(id_property, amenities);
    }	
	
	@PatchMapping("/properties/{id_property}/amenities/{id_amenity}")
	public ResponseEntity<String> update(@RequestBody Amenities amenities, @PathVariable Integer id_property, @PathVariable Integer id_amenity) {
       return amenitiesService.updateAmenities(id_property, id_amenity, amenities);
    }
}
