package com.springboot.forent.repository;

import org.springframework.data.repository.CrudRepository;

import com.springboot.forent.model.Users;

public interface UsersRepository extends CrudRepository<Users, Integer>{
	//public List<Users>findByIduser(Integer iduser);
	
}
