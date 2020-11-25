package com.springboot.forent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.forent.exception.DataNotFoundException;
import com.springboot.forent.model.Amenities;
import com.springboot.forent.repository.AmenitiesRepository;
import com.springboot.forent.repository.PropertiesRepository;

@Service
public class AmenitiesService {
	@Autowired
	private AmenitiesRepository amenitiesRepository;
	
	@Autowired
	public PropertiesRepository propertiesRepository;
	
	public List<Amenities> listPropertyAmenities(Integer id_property){
		List<Amenities> amenities = amenitiesRepository.findPropretyAmenities(id_property);
		if(!amenities.isEmpty()) {
			return amenities;
		}else {
			throw new DataNotFoundException(id_property);
		} 
	}
	
	public Amenities getPropertyAmenities(Integer id_property, Integer id_amenity) {
		Amenities amenity = amenitiesRepository.findPropertyAmenity(id_property, id_amenity);
		if(amenity != null) {
			return amenity;
		}else {
			throw new DataNotFoundException(id_amenity);
		}
        
    }
	
	public ResponseEntity<String> saveAmenities(Integer id_property, Amenities amenities) {
		Integer saveAmenityStatus = amenitiesRepository.saveAmenity(id_property, amenities.getRooms(),
				amenities.getToilets(), amenities.getBeds(), amenities.getOther_amenities());
		if(saveAmenityStatus > 0) {
			propertiesRepository.setAmenitiesId(id_property);
			return new ResponseEntity<String>("Successfully Added Amenity", HttpStatus.CREATED);
		}else {
			throw new DataNotFoundException(id_property);
		}
		
		
    }

	public ResponseEntity<String> updateAmenities(Integer id_property, Integer id_amenity, Amenities amenities) {
		 Integer updateAmenitiesStatus = amenitiesRepository.updateAmenities(id_property, id_amenity,
				 amenities.getRooms(), amenities.getToilets(), amenities.getBeds(), amenities.getOther_amenities());
		 if(updateAmenitiesStatus > 0) {
			 return new ResponseEntity<String>("Successfully Updated Amenities", HttpStatus.OK);
		 }else {
			 throw new DataNotFoundException(id_amenity);
		 }
	        	
	}    
}
