package com.springboot.forent.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public Properties saveProperty(Properties property) {
		return propertiesRepository.save(property);
    }

    public Properties getProperty(Integer id) {    	
        if(propertiesRepository.findById(id).get() == null) {
    		throw new NoSuchElementException();
    	}    
        return propertiesRepository.findById(id).get();
    }

    public String deleteProperty(Integer id) {
    	propertiesRepository.deleteById(id);
    	return "Property Deleted";
    }

	public List<Properties> getUserProperties(Integer id_user) {
		return propertiesRepository.getUserListOfProperties(id_user);
	}
    
}
