package com.springboot.forent.repository;

import java.time.OffsetDateTime;
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
			"SET location_id_location = (SELECT id_location FROM Location where id_property = ?1)  " + 
			"WHERE id_property = ?1")
	void setLocationId(Integer id_property);


	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Properties " + 
			"SET amenities_id_amenity = (SELECT id_amenity FROM Amenities where id_property = ?1) " + 
			"WHERE id_property = ?1")
	void setAmenitiesId(int id_property);


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

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value ="UPDATE properties " + 
		"SET type = ?3, name = ?4, description = ?5, price = ?6 "+
		"WHERE users_id_user = ?1 AND id_property = ?2")
	Integer updateProperty(Integer id_user, Integer id_property, String type, String name, String description,
			Float price, String updated_datetime);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value ="INSERT INTO properties( " +
			"users_id_user, type, name, description, price, created_datetime) " + 
		"SELECT  ?1, ?2, ?3, ?4, ?5, ?6 FROM users " + 
		"WHERE EXISTS (SELECT id_user FROM users WHERE id_user = ?1) " +
		"LIMIT 1")
	Integer saveProperty(Integer id_user, String type, String name, String description, Float price,
			OffsetDateTime created_datetime);
}
