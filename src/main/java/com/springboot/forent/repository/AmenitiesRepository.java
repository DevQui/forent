package com.springboot.forent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.forent.model.Amenities;

@Repository
public interface AmenitiesRepository extends CrudRepository<Amenities, Integer>{

	@Query("SELECT a FROM Amenities a WHERE a.idProperty = ?1")
	List<Amenities> findPropretyAmenities(Integer id_property);

}
