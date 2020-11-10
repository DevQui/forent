package com.springboot.forent.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.springboot.forent.model.Users;
import com.springboot.forent.repository.UsersRepository;

class UsersServiceTest {	
	@Autowired
	UsersService service;
	@MockBean
	UsersRepository repo;
	
	@Test
	@DisplayName("TEST getUsersHasResult")
	void getEmployeesHasResult() throws Exception {
		// Mocked the employee and the repo
		Users user1 = new Users(1, "host","John", "Middle Name", "Last-Name-John", "john@gmail.com", "+6911111111111", "password123","2020-11-09 11:00:00");
		Users user2 = new Users(2, "tenant","Jane", "Middle Name", "Last-Name-Jane", "jane@gmail.com", "+6911111111112", "password123","2020-11-09 11:00:00");
		Users user3 = new Users(3, "tenant","James", "Middle Name", "Last-Name-James", "james@gmail.com", "+6911111111113", "password123","2020-11-09 11:00:00");
		List<Users> list = new ArrayList<Users>();
		list.add(user1);
		list.add(user2);
		list.add(user3);
		/*doReturn(list).when(repo).getUsers();
		// Call service
		List<Users> returnedList = (List<Employee>) service.getEmployees();
		// Validate
		Assertions.assertFalse(returnedList.isEmpty(), "No result.");
		Assertions.assertSame(returnedList.get(0), user1, "Employee should be the same.");
		Assertions.assertEquals(returnedList.get(2).getName(), "James");*/
	}

}
