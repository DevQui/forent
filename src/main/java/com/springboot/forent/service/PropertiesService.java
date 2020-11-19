package com.springboot.forent.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.forent.model.Properties;
import com.springboot.forent.repository.PropertiesRepository;

@Service
public class PropertiesService {
	@Autowired
	private PropertiesRepository propertiesRepository;
	
	public List<Properties> listAllProperties(){
		return (List<Properties>) propertiesRepository.findAllWithUserInfo();
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

    public ResponseEntity<String> deleteProperty(Integer id_user, Integer id_property) {
    	Integer count = propertiesRepository.deletePropertyOfUser(id_user, id_property);
    	String response = "Property Deleted";
    	try{
    		if(count <= 0) {
    			response = "Property of the User is NOT FOUND";
    			throw new Exception();
    		}	
    	}catch(Exception ex) {
    		return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
    	}
    	return new ResponseEntity<String>(response, HttpStatus.OK);
    }

	public List<Properties> getUserProperties(Integer id_user) {
		return propertiesRepository.getUserListOfProperties(id_user);
	}
    
}
