package com.springboot.forent.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.forent.exception.DataNotFoundException;
import com.springboot.forent.exception.NoDataFoundException;
import com.springboot.forent.model.Properties;
import com.springboot.forent.repository.PropertiesRepository;

@Service
public class PropertiesService {
	@Autowired
	private PropertiesRepository propertiesRepository;
		
	public List<Properties> listAllProperties(){
		List<Properties> properties = (List<Properties>) propertiesRepository.findAllWithUserInfo();
		if(!properties.isEmpty()) {
			return properties;
		}else{
			throw new NoDataFoundException();
		}
	}
	
	public List<Properties> getUserProperties(Integer id_user) {
		List<Properties> propertiesOfUser = propertiesRepository.getUserListOfProperties(id_user);
		if(!propertiesOfUser.isEmpty()) {
			return propertiesOfUser;
		}else {
			throw new DataNotFoundException(id_user);
		}
	}
	
	public ResponseEntity<Properties> getProperty(Integer id) {    	
		try {
			Properties property = propertiesRepository.findById(id).get();
            return new ResponseEntity<Properties>(property, HttpStatus.OK);
        }catch (NoSuchElementException ex) {
        	throw new DataNotFoundException(id);
        }
    }
	
	public ResponseEntity<Properties> getPropertyOfUser(Integer id_user, Integer id_property) {    	
		Properties property = propertiesRepository.findPropertyOfUser(id_user, id_property);
		if(property != null) {
            return new ResponseEntity<Properties>(property, HttpStatus.OK);
        }else {
        	throw new DataNotFoundException(id_property);
        }
    }
	
	
	public ResponseEntity<Properties> addProperty(Integer id_user, Properties property){
			OffsetDateTime current = OffsetDateTime.now();
			String created_datetime = current.toString();	
			property.setCreated_datetime(created_datetime);
			property.setUsers_id_user(id_user);
			Properties propertySaved = propertiesRepository.save(property);
			return new ResponseEntity<Properties>(propertySaved, HttpStatus.CREATED);
		
    }
	
	public ResponseEntity<Properties> updateProperty(Integer id_user, Integer id_property, Properties property) {
		try {
			System.out.println(id_user + " " + id_property);
        	Properties existProperty = propertiesRepository.findPropertyOfUser(id_user, id_property);
        	System.out.println(existProperty);
        	OffsetDateTime current = OffsetDateTime.now();
    		String updated_datetime = current.toString();
    		
			existProperty.setType(property.getType());
			existProperty.setName(property.getName());
			existProperty.setDescription(property.getDescription());
			existProperty.setPrice(property.getPrice());
			existProperty.setUpdated_datetime(updated_datetime);
	    	propertiesRepository.save(existProperty);
            return new ResponseEntity<Properties>(existProperty, HttpStatus.OK);
        } catch (Exception ex) {
        	throw new DataNotFoundException(id_property);
        }
	}
    

    public ResponseEntity<String> deleteProperty(Integer id_user, Integer id_property) {
    	Integer isDeleted = propertiesRepository.deletePropertyOfUser(id_user, id_property);
    	if(isDeleted > 0) {
    		return new ResponseEntity<String>("Property Deleted", HttpStatus.OK);
    	}else{
    		throw new DataNotFoundException(id_property);
    	}
    }
}
