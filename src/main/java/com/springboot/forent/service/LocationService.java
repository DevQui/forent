package com.springboot.forent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.forent.model.Location;
import com.springboot.forent.repository.LocationRepository;

@Service
public class LocationService {
	@Autowired
	private LocationRepository locationRepository;
	
	public List<Location> listAllLocation(){
		 return (List<Location>) locationRepository.findAll();
	}
	
	public void saveLocation(Location location) {
		locationRepository.save(location);
    }

    public Location getLocation(Integer id) {
        return locationRepository.findById(id).get();
    }

    public void deleteLocation(Integer id) {
    	locationRepository.deleteById(id);
    }
}
