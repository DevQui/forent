package com.springboot.forent.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.forent.model.Reviews;


@Repository
public interface ReviewsRepository extends CrudRepository<Reviews, Integer>{
	@Query("SELECT r FROM Reviews r WHERE r.id_property = ?1")
	Optional<Reviews> findByIdProperty(Integer idProperty);
	
	@Query("SELECT r FROM Reviews r WHERE r.id_property = ?1 AND r.id_review = ?2")
	Optional<Reviews> findByIdReviewIdProprerty(Integer id_property, Integer id);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("DELETE FROM Reviews r WHERE r.id_property = ?1 AND r.id_review = ?2")
	void deleteReviewFromProperty(Integer id_property, Integer id);
}
