package com.springboot.forent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.forent.model.Reviews;


@Repository
public interface ReviewsRepository extends CrudRepository<Reviews, Integer>{
	@Query("SELECT r FROM Reviews r WHERE r.id_property = ?1")
	List<Reviews> findByIdProperty(Integer idProperty);
	
	@Query("SELECT r FROM Reviews r WHERE r.id_property = ?1 AND r.id_review = ?2")
	Reviews findByIdReviewIdProprerty(Integer id_property, Integer id);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("DELETE FROM Reviews r WHERE r.id_property = ?1 AND r.id_review = ?2")
	Integer deleteReviewFromProperty(Integer id_property, Integer id);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value ="INSERT INTO reviews(" +
			"id_property, id_user, rating, comment, created_datetime) " + 
		"SELECT  ?1, ?2, ?3, ?4, ?5 FROM reviews " + 
		"WHERE EXISTS (SELECT id_property FROM properties WHERE id_property = ?1) AND " +
		"EXISTS (SELECT id_user FROM users WHERE id_user = ?2)" +
		"LIMIT 1")
	Integer saveReview(Integer id_property, int id_user, int rating, String comment, String created_datetime);
}
