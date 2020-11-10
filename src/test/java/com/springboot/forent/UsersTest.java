package com.springboot.forent;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.time.OffsetDateTime;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.springboot.forent.model.Users;
import com.springboot.forent.service.UsersService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ForentApplication.class)
@AutoConfigureMockMvc
class UsersTest {
	
	@Autowired
	protected MockMvc mvc;
	
	@Autowired
	UsersService service;
	
	@Test
	void test() {
		//fail("Not yet implemented");
	}	
	
	@Test
	@Rollback(false)
	public void testPostUser() throws Exception {
		String[] types = {"host", "tenant", "admin"};
		OffsetDateTime current = OffsetDateTime.now();
		String created_datetime = current.toString();
		Users user = new Users("host", 
								"Dev123",
								"Narvaez", 
								"Quisido", 
								"qdevorah01499@gmail.com", 
								"+639111111111",
								"password123",
								created_datetime);

		service.saveUser(user);
	    assertThat(Arrays.asList(types).contains(user.getType()));
	}
	
	@Test
	public void testGetUser() throws Exception{
		Users user = service.getUser(5);
		assertEquals(user.getLast_name(), "Quisido");
	}
	
	@Test
	public void testPutUser() throws Exception{
		Users user = service.getUser(9);
		String original_firstname = user.getFirst_name();
		user.setFirst_name(user.getFirst_name()+" (updated2)");
		service.saveUser(user);
		assertEquals(original_firstname+" (updated2)", user.getFirst_name());
	}
	
	@Test
	public void testDeleteUser() throws Exception{
    
		/*Users user = repo.findById(1).get();
	    repo.deleteById(user.getId_user());
	    Users deletedUser = repo.findById(1).get();
	    assertThat(deletedUser);*/

	    String uri = "http://localhost:8080/users/6";
	    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
	    int status = mvcResult.getResponse().getStatus();
	    assertEquals(200, status);
	}
}
