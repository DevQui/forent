package com.springboot.forent.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.forent.model.Reviews;

public interface ReviewsRepository extends CrudRepository<Reviews, Integer>{

}
