package com.springboot.forent.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.springboot.forent.model.Amenities;
import com.springboot.forent.model.Location;
import com.springboot.forent.model.Properties;
import com.springboot.forent.model.Users;
import com.springboot.forent.repository.PropertiesRepository;

@Service
public class PropertiesService {
	@Autowired
	private PropertiesRepository propertiesRepository;
	
	public List<Properties> listAllProperties(){
		 return (List<Properties>) propertiesRepository.findAll();
	}
	
	public void saveProperty(Properties property) {
		propertiesRepository.save(property);
    }

    public Properties getProperty(Integer id) {
    	RestTemplate restTemplate = new RestTemplate();        	
        Properties property = propertiesRepository.findById(id).get();
        if(propertiesRepository.findById(id).get() == null) {
    		throw new NoSuchElementException();
    	}
        Location location = restTemplate.getForObject("http://localhost:8080/location/"+property.getId_location().getId_location(), Location.class);
        Amenities amenities = restTemplate.getForObject("http://localhost:8080/amenities/"+property.getId_amenities().getId_amenities(), Amenities.class);
        Users user = restTemplate.getForObject("http://localhost:8080/users/"+property.getId_user().getId_user(), Users.class);
    	
    	return new Properties(property, location, amenities, user);    
        //return propertiesRepository.findById(id).get();
    }

    public void deleteProperty(Integer id) {
    	propertiesRepository.deleteById(id);
    }
}
