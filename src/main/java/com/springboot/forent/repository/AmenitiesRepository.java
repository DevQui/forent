package com.springboot.forent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.forent.model.Amenities;

@Repository
public interface AmenitiesRepository extends CrudRepository<Amenities, Integer>{

	@Query(nativeQuery = true, value ="SELECT a.* FROM Amenities a WHERE a.id_property = ?1 LIMIT 1")
	List<Amenities> findPropretyAmenities(Integer id_property);

	@Query("SELECT a FROM Amenities a WHERE a.idProperty = ?1 AND a.id_amenity = ?2")
	Amenities findPropertyAmenity(Integer id_property, Integer id_amenity);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value ="INSERT INTO amenities( " +
			"id_property, rooms, toilets, beds, other_amenities) " + 
		"SELECT  ?1, ?2, ?3, ?4, ?5 FROM properties " + 
		"WHERE EXISTS (SELECT id_property FROM properties WHERE id_property = ?1 AND users_id_user = ?6) " +
		"AND NOT EXISTS( SELECT id_property FROM amenities WHERE id_property = ?1) " +
		"LIMIT 1")
	Integer saveAmenity(Integer id_property, int rooms, int toilets, int beds, String other_amenities, Integer id_user);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Amenities " + 
			"SET rooms = ?3 , toilets = ?4, beds = ?5, other_amenities = ?6 " + 
			"WHERE id_property = ?1 AND id_amenity = ?2")
	Integer updateAmenities(Integer id_property, Integer id_amenity, int rooms, int toilets, int beds,
			String other_amenities);

}
