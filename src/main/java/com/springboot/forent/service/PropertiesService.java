package com.springboot.forent.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.forent.model.Properties;
import com.springboot.forent.repository.PropertiesRepository;

@Service
public class PropertiesService {
	@Autowired
	private PropertiesRepository propertiesRepository;
	
	public List<Properties> listAllProperties(){
		/*List<Properties> propertiesAll;
		List<Properties> propertiesData = new ArrayList<>();
		
		propertiesAll = propertiesRepository.findAll();
		
		for(Properties property : propertiesAll) {
			propertiesData.add(List<Properties> propertiesRepository.findByAmenitiesIdProperty(property.getId_property()));
		}
		return propertiesData;*/
		return (List<Properties>) propertiesRepository.findAll();
	}
	
	public void saveProperty(Properties property) {
		propertiesRepository.save(property);
    }

    public Properties getProperty(Integer id) {    	
        if(propertiesRepository.findById(id).get() == null) {
    		throw new NoSuchElementException();
    	}    
        return propertiesRepository.findById(id).get();
    }

    public void deleteProperty(Integer id) {
    	propertiesRepository.deleteById(id);
    }
    
}
