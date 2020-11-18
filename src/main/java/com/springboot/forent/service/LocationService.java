package com.springboot.forent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		 return (List<Location>) locationRepository.findAll();
	}
	
	public Location saveLocation(Location location) {
		Location loc = locationRepository.save(location);
		propertiesRepository.setLocationId(loc.getId_location(), loc.getId_property());
		return loc;
    }

    public Location getLocation(Integer id_property, Integer id) {
        return locationRepository.findIdLocationFromProperty(id_property, id);
    }

}
