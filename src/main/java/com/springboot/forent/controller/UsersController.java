package com.springboot.forent.controller;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.forent.model.Users;
import com.springboot.forent.service.UsersService;

@RestController
public class UsersController {
	@Autowired
    private UsersService usersService;
	
	@GetMapping("/users")
    public List<Users> list() {
        return usersService.listAllUsers();
    }
	
	@PostMapping("/users")
	public ResponseEntity<Users> add(@RequestBody Users user) {
		try {
			HttpHeaders header = new HttpHeaders();
			header.setLocation(new URI("/users"));
			Users response = usersService.saveUser(user);
			return new ResponseEntity<Users>(response,header,HttpStatus.CREATED);
		}catch(Exception ex) {
			return new ResponseEntity<>(user,HttpStatus.PRECONDITION_REQUIRED);
		}
        
    }
	
	@GetMapping("/users/{id}")
	public ResponseEntity<Users> get(@PathVariable Integer id) {
		try {
			Users user = usersService.getUser(id);
			return new ResponseEntity<Users>(user, HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
    @PutMapping("/users/{id}")
    public ResponseEntity<Users> update(@RequestBody Users user, @PathVariable Integer id) {
        try {
            Users existUser = usersService.getUser(id);
            if(existUser != null) {
            	OffsetDateTime current = OffsetDateTime.now();
        		String updated_datetime = current.toString();
        	
				existUser.setFirst_name(user.getFirst_name());
				existUser.setMiddle_name(user.getMiddle_name());
    			existUser.setLast_name(user.getLast_name());
    			existUser.setEmail(user.getEmail());
    			existUser.setPhone_number(user.getPhone_number());
    			existUser.setUser_password(user.getUser_password());
    	    	existUser.setUpdated_datetime(updated_datetime);
    	    	usersService.saveUser(existUser);
        				
            }
            return new ResponseEntity<Users>(user,HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	
    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
		String response = usersService.deleteUser(id);
		return new ResponseEntity<String>(response,HttpStatus.OK);
    }
    
}
