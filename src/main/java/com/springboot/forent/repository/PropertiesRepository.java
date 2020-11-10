package com.springboot.forent.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.forent.model.Properties;

public interface PropertiesRepository extends CrudRepository<Properties, Integer>{
	//@Query("FROM Properties LEFT JOIN Users.id_user = Properties.id_user")
	public List<Properties>findByIduser(Integer iduser);
	public List<Properties>findByIdlocation(Integer idlocation);
	public List<Properties>findByIdamenities(Integer idamenities);
}
