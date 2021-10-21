package com.springboot.forent.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@GetMapping("/location/{id_location}")
	public Location getLocation(@PathVariable Integer id_location) {
        return locationService.getLocation(id_location);
	}
	
	@GetMapping("properties/{id_property}/location")
    public Location getLocationByPropertyID(@PathVariable Integer id_property) {
        return locationService.getLocationByPropertyID(id_property);
	}	
	
	@GetMapping("properties/{id_property}/location/{id_location}")
    public Location getPropertyLocation(@PathVariable Integer id_property, @PathVariable Integer id_location) {
        return locationService.getPropertyLocation(id_property, id_location);
	}	
	
	
	@PostMapping("/users/{id_user}/properties/{id_property}/location")
	public ResponseEntity<String> addPropertyLocation(@RequestBody @Valid Location location, @PathVariable Integer id_property, @PathVariable Integer id_user) {
		return locationService.savePropertyLocation(id_property, location, id_user);
    }
	
	@PatchMapping("/properties/{id_property}/location/{id_location}")
	public ResponseEntity<String> updatePropertyLocation(@RequestBody @Valid Location location, @PathVariable Integer id_location, @PathVariable Integer id_property) {
        return locationService.updatePropertyLocation(location, id_property, id_location);
    }

}
