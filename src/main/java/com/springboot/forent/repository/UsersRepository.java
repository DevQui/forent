package com.springboot.forent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.forent.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer>{
	//public List<Users>findByIduser(Integer iduser);
}
