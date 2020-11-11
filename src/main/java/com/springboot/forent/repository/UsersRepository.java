package com.springboot.forent.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.forent.model.Users;

@Repository
public interface UsersRepository extends CrudRepository<Users, Integer>{
}
