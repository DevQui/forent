package com.springboot.forent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		 return (List<Amenities>) amenitiesRepository.findPropretyAmenities(id_property);
	}
	
	public Amenities saveAmenities(Amenities amenities) {
		Amenities amenity = amenitiesRepository.save(amenities); 
		propertiesRepository.setAmenitiesId(amenity.getId_amenity(), amenity.getId_property());
		return amenity;
    }

    public Amenities getAmenities(Integer id) {
        return amenitiesRepository.findById(id).get();
    }
}
