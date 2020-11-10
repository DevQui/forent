package com.springboot.forent.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UsersServiceTest {

	/*@Test
	void test() {
		fail("Not yet implemented");
	}*/
	
	@Autowired
	EmployeeService service;
	@MockBean
	EmployeeRepository repo;
	@Test
	@DisplayName("TEST getEmployeesHasResult")
	void getEmployeesHasResult() throws Exception {
		// Mocked the employee and the repo
		Employee emp1 = new Employee(1, "John", "Manager", 10);
		Employee emp2 = new Employee(2, "Jane", "Director", 15);
		Employee emp3 = new Employee(3, "James", "Supervisor", 5);
		List<Employee> list = new ArrayList<Employee>();
		list.add(emp1);
		list.add(emp2);
		list.add(emp3);
		doReturn(list).when(repo).getEmployees();
		// Call service
		List<Employee> returnedList = (List<Employee>) service.getEmployees();
		// Validate
		Assertions.assertFalse(returnedList.isEmpty(), "No result.");
		Assertions.assertSame(returnedList.get(0), emp1, "Employee should be the same.");
		Assertions.assertEquals(returnedList.get(2).getName(), "James");
	}

}
