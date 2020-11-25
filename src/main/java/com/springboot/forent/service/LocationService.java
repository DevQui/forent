package com.springboot.forent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.forent.exception.DataNotFoundException;
import com.springboot.forent.exception.NoDataFoundException;
import com.springboot.forent.model.Location;
import com.springboot.forent.repository.LocationRepository;
import com.springboot.forent.repository.PropertiesRepository;

@Service
public class LocationService {
	@Autowired
	private LocationRepository locationRepository;
	
	@Autowired
	public PropertiesRepository propertiesRepository;
	
	public List<Location> listAllLocation(){
		 List<Location> locations = (List<Location>) locationRepository.findAll();
		 if(!locations.isEmpty()) {
			 return locations;
		 }else {
			 throw new NoDataFoundException();
		 }
	}
	
	public ResponseEntity<String> savePropertyLocation(Integer id_property, Location location) {
		Integer saveLocationStatus = locationRepository.savePropertyLocation(id_property, location.getTown(), 
				location.getCity(), location.getRegion(), location.getCountry());
		if(saveLocationStatus > 0) {
			propertiesRepository.setLocationId(id_property);
			return new ResponseEntity<String>("Successfully Added Location", HttpStatus.CREATED);
		}else {
			throw new DataNotFoundException(id_property);
		}
    }

    public Location getPropertyLocation(Integer id_property, Integer id_location) {
        Location location =  locationRepository.findIdLocationFromProperty(id_property, id_location);
        if(location != null) {
        	return location;
        }else {
        	throw new DataNotFoundException(id_location);
        }
    }

	public ResponseEntity<String> updatePropertyLocation(Location location, Integer id_property, Integer id_location) {
		Integer updatePropertyLocationStatus = locationRepository.updatePropertyLocation(id_property, id_location, 
				location.getTown(), location.getCity(), location.getRegion(), location.getCountry());
    		
    	if(updatePropertyLocationStatus > 0) {
    		return new ResponseEntity<String>("Successfully Updated Property Location", HttpStatus.OK);
    	}else {
    		throw new DataNotFoundException(id_location);
    	}
		
	}

    
    
}
