package com.springboot.forent.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.forent.model.Location;

@Repository
public interface LocationRepository extends CrudRepository<Location, Integer>{

}
