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
import org.springframework.web.client.RestTemplate;

import com.springboot.forent.model.Amenities;
import com.springboot.forent.model.Location;
import com.springboot.forent.model.Properties;
import com.springboot.forent.model.Users;
import com.springboot.forent.service.AmenitiesService;
import com.springboot.forent.service.LocationService;
import com.springboot.forent.service.PropertiesService;

@RestController
public class PropertiesController {
	@Autowired
    private PropertiesService propertiesService;
	
	@GetMapping("/properties")
    public List<Properties> list() {
        return propertiesService.listAllProperties();
    }
	
	/*@PostMapping("/properties")
    public void add(@RequestBody Properties property, Location location, Amenities amenities) {
		OffsetDateTime current = OffsetDateTime.now();
		String created_datetime = current.toString();	
		property.setCreated_datetime(created_datetime);
        propertiesService.saveProperty(property);
        
        location.setId_property(property.getId_property());
        locationService.saveLocation(location);
        amenities.setId_property(property.getId_property());
        amenitiesService.saveAmenities(amenities);
    }*/
	
	@GetMapping("/properties/{id}")
	public ResponseEntity<Properties> get(@PathVariable Integer id){
		try {
            Properties property = propertiesService.getProperty(id);
            return new ResponseEntity<Properties>(property, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Properties>(HttpStatus.NOT_FOUND);
        }
	}
	
    /*@PutMapping("/properties/{id}")
    public ResponseEntity<Properties> update(@RequestBody Properties property, @PathVariable Integer id) {
        try {
        	Properties existProperty = propertiesService.getProperty(id);
        	
        	//Location location = locationService.getLocation(existProperty.getId_location().getId_location());
        	//Amenities amenities = amenitiesService.getAmenities(existProperty.getId_amenities().getId_amenities());
        	
        	//Location location = locationService.getLocation(existProperty.getLocation().getId_location());
        	//Amenities amenities = amenitiesService.getAmenities(existProperty.getAmenities().getId_amenity());
        	
        	if(existProperty != null) {
            	OffsetDateTime current = OffsetDateTime.now();
        		String updated_datetime = current.toString();
        		
        		try{
        			if(property.getType() != null || property.getName() != null ||
        					property.getPrice() != null || property.getDescription() != null ||
    						!property.getType().isEmpty() || !property.getName().isEmpty() ||
    						!property.getDescription().isEmpty()) {
        				
        				existProperty.setType(existProperty.getType());
        				existProperty.setName(existProperty.getName());
        				existProperty.setPrice(existProperty.getPrice());
        				existProperty.setDescription(existProperty.getDescription());
        				existProperty.setUpdated_datetime(updated_datetime);
            	    	propertiesService.saveProperty(existProperty);
            	    	
            	    	location.setTown(location.getTown());
            	    	location.setCity(location.getTown());
            	    	location.setRegion(location.getRegion());
            	    	location.setCountry(location.getCountry());
            	    	locationService.saveLocation(location);
            	    	
            	    	amenities.setRooms(amenities.getRooms());
            	    	amenities.setToilets(amenities.getToilets());
            	    	amenities.setBeds(amenities.getBeds());
            	    	amenities.setOther_amenities(amenities.getOther_amenities());
            	    	amenitiesService.saveAmenities(amenities);
        			}
        		
        		}catch(NullPointerException ex) {
        			return new ResponseEntity<>(HttpStatus.PRECONDITION_REQUIRED);
        		}
        	}
            return new ResponseEntity<Properties>(property, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/properties/{id}")
    public void delete(@PathVariable Integer id) {
    	propertiesService.deleteProperty(id);
    	Properties property = propertiesService.getProperty(id);
    	locationService.deleteLocation(property.getLocation().getId_location());
    }*/
    
    /*@GetMapping("/properties/{id}/reviews")
    public Properties getReviews(@PathVariable Integer id) {
    	 try {
         	RestTemplate restTemplate = new RestTemplate();        	
             Properties property = propertiesService.getProperty(id);
             Location location = restTemplate.getForObject("http://localhost:8080/location/"+property.getId_location().getId_location(), Location.class);
             Amenities amenities = restTemplate.getForObject("http://localhost:8080/amenities/"+property.getId_amenities().getId_amenities(), Amenities.class);
             Users user = restTemplate.getForObject("http://localhost:8080/users/"+property.getId_user().getId_user(), Users.class);
             //Reviews review = restTemplate.getForObject("http://localhost:8080/reviews/"+property.getId_review(), Reviews.class);
             return new Properties(property, location, amenities, user);
             //return new ResponseEntity<Properties>(property, HttpStatus.OK);
         } catch (NoSuchElementException e) {;
             //return new ResponseEntity<Properties>(HttpStatus.NOT_FOUND);
         	return null;	
         }
    }*/
}
