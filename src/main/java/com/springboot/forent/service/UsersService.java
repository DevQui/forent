package com.springboot.forent.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.springboot.forent.model.Users;
import com.springboot.forent.repository.UsersRepository;

@Service
public class UsersService {
	@Autowired
	private UsersRepository usersRepository;
	
	public List<Users> listAllUsers(){
		return (List<Users>) usersRepository.findAll();
	}
	
	public void saveUser(Users user) {
		OffsetDateTime current = OffsetDateTime.now();
		String created_datetime = current.toString();	
		user.setCreated_datetime(created_datetime);
		if(StringUtils.isEmpty(user.getFirst_name()) || StringUtils.isEmpty(user.getLast_name()) || 
				StringUtils.isEmpty(user.getEmail()) || StringUtils.isEmpty(user.getPhone_number()) ||
				StringUtils.isEmpty(user.getUser_password())) {
    		throw new NoSuchElementException();
    	}
        usersRepository.save(user);
    }

    public Users getUser(Integer id) {
    	if(usersRepository.findById(id).get() == null) {
    		throw new NoSuchElementException();
    	}
        return usersRepository.findById(id).get();
    }
    	

    public void deleteUser(Integer id) {
        usersRepository.deleteById(id);
    }
}
