package com.springboot.forent.service;

import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.forent.model.Users;
import com.springboot.forent.repository.UsersRepository;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@WebMvcTest(UsersService.class)
class UsersServiceTest {	
	@Autowired
	UsersService service;
	
	@MockBean
	UsersRepository repo;
	
	@Test
	@DisplayName("TEST getUsersHasResult")
	void getUsersHasResult() throws Exception {
		// Mocked the users and the repo
		Users user1 = new Users(1, "host","John", "Middle Name", "Last-Name-John", "john@gmail.com", "+6911111111111", "password123","2020-11-09 11:00:00");
		Users user2 = new Users(2, "tenant","Jane", "Middle Name", "Last-Name-Jane", "jane@gmail.com", "+6911111111112", "password123","2020-11-09 11:00:00");
		Users user3 = new Users(3, "tenant","James", "Middle Name", "Last-Name-James", "james@gmail.com", "+6911111111113", "password123","2020-11-09 11:00:00");
		List<Users> list = new ArrayList<Users>();
		list.add(user1);
		list.add(user2);
		list.add(user3);
		doReturn(list).when(repo).findAll();
		// Call service
		List<Users> returnedList = (List<Users>) service.listAllUsers();
		// Validate
		Assertions.assertFalse(returnedList.isEmpty(), "No result.");
		Assertions.assertSame(returnedList.get(0), user1, "User should be the same.");
		Assertions.assertEquals(returnedList.get(2).getFirst_name(), "James");
	}
	
	@Test
	@DisplayName("TEST getUserByID")
	void getUserByID() throws Exception{
		Users user1 = new Users(1, "host","John", "Middle Name", "Last-Name-John", "john@gmail.com", "+6911111111111", "password123","2020-11-09 11:00:00");
		doReturn(Optional.of(user1)).when(repo).findById(1);
		
		Users user = service.getUser(1);
		
		Assertions.assertEquals(user.getId_user(),1);
		Assertions.assertEquals(user.getType(),"host");
		Assertions.assertEquals(user.getFirst_name(),"John");
		Assertions.assertEquals(user.getMiddle_name(),"Middle Name");
		Assertions.assertEquals(user.getLast_name(),"Last-Name-John");
		Assertions.assertEquals(user.getEmail(),"john@gmail.com");
		Assertions.assertEquals(user.getPhone_number(),"+6911111111111");
		Assertions.assertEquals(user.getUser_password(),"password123");
	}
	
	@Test
	@DisplayName("TEST saveUser")
	void saveUser() throws Exception{
		Users user = new Users(1, "host","John", "Middle Name", "Last-Name-John", "john@gmail.com", "+6911111111111", "password123","2020-11-09 11:00:00");
		doReturn(user).when(repo).save(user);
		
		Users addedUser = service.saveUser(user);
		
		Assertions.assertEquals(addedUser.getId_user(),1);
		Assertions.assertEquals(addedUser.getType(),"host");
		Assertions.assertEquals(addedUser.getFirst_name(),"John");
		Assertions.assertEquals(addedUser.getMiddle_name(),"Middle Name");
		Assertions.assertEquals(addedUser.getLast_name(),"Last-Name-John");
		Assertions.assertEquals(addedUser.getEmail(),"john@gmail.com");
		Assertions.assertEquals(addedUser.getPhone_number(),"+6911111111111");
		Assertions.assertEquals(addedUser.getUser_password(),"password123");		
	}
}
