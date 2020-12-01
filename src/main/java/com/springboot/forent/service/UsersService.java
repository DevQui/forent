package com.springboot.forent.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.forent.exception.NoDataFoundException;
import com.springboot.forent.model.Users;
import com.springboot.forent.repository.UsersRepository;

@Service
public class UsersService {
	@Autowired
	private UsersRepository usersRepository;
	
	public List<Users> listAllUsers(){
		List<Users> users =  (List<Users>) usersRepository.findAll();
		if(!users.isEmpty()) {
			return users;
		}else {
			throw new NoDataFoundException();
		}
	}
		
	public ResponseEntity<String> saveUser(Users user) {
		OffsetDateTime current = OffsetDateTime.now();
		String created_datetime = current.toString();
		
		Integer saveUserStatus = usersRepository.saveUser(user.getType(), user.getFirst_name(),
				user.getMiddle_name(), user.getLast_name(), user.getEmail(), user.getPhone_number(),
				user.getUser_password(), created_datetime);
		
		if(saveUserStatus > 0) {
			return new ResponseEntity<String>("Successfully Added User", HttpStatus.CREATED);
		}else {
			throw new NoDataFoundException();
		}
    }

    public Users getUser(Integer id) {
    	if(usersRepository.findById(id).get() == null) {
    		throw new NoSuchElementException();
    	}
        return usersRepository.findById(id).get();
    }
    	

    public String deleteUser(Integer id) {
        usersRepository.deleteById(id);
        return "User Deleted";
    }
}
