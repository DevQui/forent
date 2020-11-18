package com.springboot.forent.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.forent.model.Location;

@Repository
public interface LocationRepository extends CrudRepository<Location, Integer>{
	
	@Query("SELECT loc FROM Location loc WHERE loc.id_property = ?1 AND loc.id_location = ?2")
	Location findIdLocationFromProperty(Integer id_property, Integer id);
}
