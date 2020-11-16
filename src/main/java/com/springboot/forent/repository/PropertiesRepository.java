package com.springboot.forent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.forent.model.Properties;

@Repository
public interface PropertiesRepository extends JpaRepository<Properties, Integer>{
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Properties " + 
			"SET location_id_location = ?1 " + 
			"WHERE id_property = ?2")
	void setLocationId(Integer id_location, Integer id_property);


	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Properties " + 
			"SET amenities_id_amenity = ?1 " + 
			"WHERE id_property = ?2")
	void setAmenitiesId(int id_amenity, int id_property);
}
