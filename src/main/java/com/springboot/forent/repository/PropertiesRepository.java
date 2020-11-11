package com.springboot.forent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.forent.model.Properties;

@Repository
public interface PropertiesRepository extends JpaRepository<Properties, Integer>{
	//public List<Properties> findByAmenitiesIdProperty(Integer id);
}
