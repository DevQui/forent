package com.springboot.forent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.forent.model.Properties;

@Repository
public interface PropertiesRepository extends CrudRepository<Properties, Integer>{
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


	@Query("SELECT p FROM Properties p WHERE p.users.id_user = ?1")
	List<Properties> getUserListOfProperties(Integer id_user);
	
	@Query("SELECT p,u FROM Properties p LEFT JOIN Users u ON p.users_id_user = u.id_user")
	List<Properties> findAllWithUserInfo();

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("DELETE Properties " + 
			"WHERE users_id_user = ?1 AND id_property = ?2")
	Integer deletePropertyOfUser(Integer id_user, Integer id_property);

	@Query("SELECT p FROM Properties p WHERE p.users.id_user = ?1 AND p.id_property = ?2")
	Properties findPropertyOfUser(Integer id_user, Integer id_property);
}
