package com.springboot.forent.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.forent.model.Amenities;

@Repository
public interface AmenitiesRepository extends CrudRepository<Amenities, Integer>{

}
