package com.springboot.forent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.forent.model.Amenities;
import com.springboot.forent.repository.AmenitiesRepository;

@Service
public class AmenitiesService {
	
	
	private AmenitiesRepository amenitiesRepository;
	
	public List<Amenities> listAllAmenities(){
		 return (List<Amenities>) amenitiesRepository.findAll();
	}
	
	public void saveAmenities(Amenities amenities) {
		amenitiesRepository.save(amenities);
    }

    public Amenities getAmenities(Integer id) {
        return amenitiesRepository.findById(id).get();
    }

    public String deleteAmenities(Integer id) {
    	amenitiesRepository.deleteById(id);
        return "User "+id+" has been deleted.";
    }
}
