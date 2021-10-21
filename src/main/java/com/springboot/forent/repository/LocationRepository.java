package com.springboot.forent.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.forent.model.Location;

@Repository
public interface LocationRepository extends CrudRepository<Location, Integer>{
	
	@Query("SELECT loc FROM Location loc WHERE loc.id_property = ?1 AND loc.id_location = ?2")
	Location findIdLocationFromProperty(Integer id_property, Integer id);
	
	@Query("SELECT loc FROM Location loc WHERE loc.id_location = ?1")
	Location getLocation(Integer id_location);
	
	@Query("SELECT loc FROM Location loc WHERE loc.id_property = ?1")
	Location getLocationByPropertyID(Integer id_property);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value ="INSERT INTO location(" +
			"id_property, town, city, region, country) " + 
		"SELECT  ?1, ?2, ?3, ?4, ?5 FROM properties " + 
		"WHERE EXISTS (SELECT id_property FROM properties WHERE id_property = ?1 AND users_id_user = ?6) " +
		"AND NOT EXISTS( SELECT id_property FROM location WHERE id_property = ?1)" +
 		"LIMIT 1")
	void savePropertyLocation(Integer id_property, String town, String city, String region, String country, Integer id_user);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Location " + 
			"SET id_property = ?1 , town = ?3, city = ?4,region = ?5, country = ?6 " + 
			"WHERE id_property = ?1 AND id_location = ?2")
	Integer updatePropertyLocation(Integer id_property, Integer id_location, String town, String city, String region,
			String country);
}
