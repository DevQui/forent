package com.springboot.forent.repository;

import java.time.OffsetDateTime;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.forent.model.Users;

@Repository
public interface UsersRepository extends CrudRepository<Users, Integer>{

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value ="INSERT INTO users(" +
			"type, first_name, middle_name, last_name, email, phone_number, user_password, created_datetime) " + 
			"VALUES(?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8)")
	Integer saveUser(String type, String first_name, String middle_name, String last_name, String email,
			String phone_number, String user_password, OffsetDateTime created_datetime);

	/*@Query(nativeQuery = true, value ="INSERT INTO users(" +
	"type, first_name, middle_name, last_name, email, phone_number, user_password, created_datetime) " + 
"SELECT  ?1, ?2, 0, ?3, ?4 FROM users " + 
"WHERE EXISTS (SELECT id_property FROM properties WHERE id_property = ?1) AND " +
"EXISTS (SELECT id_user FROM users WHERE id_user = ?2) " +
"LIMIT 1")*/
}
