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

import com.springboot.forent.service.AmenitiesService;
import com.springboot.forent.service.LocationService;
import com.springboot.forent.service.PropertiesService;
import com.springboot.forent.service.UsersService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ForentApplication.class)
@AutoConfigureMockMvc
class PropertiesTest {
	
	@Autowired
	protected MockMvc mvc;
	
	@Autowired
	PropertiesService service;
	
	@Autowired
	UsersService usersService;
	
	@Autowired
	LocationService locationService;
	
	@Autowired
	AmenitiesService amenitiesService;

	@Test
	void test() {
		//fail("Not yet implemented");
	}
	
	@Test
	@Rollback(false)
	public void testPostProperty() throws Exception {
		/*String[] types = {"apartment", "condominium", "detached", "bungalow"};
		OffsetDateTime current = OffsetDateTime.now();
		String created_datetime = current.toString();
		Users user = usersService.getUser(14);
		Location location = locationService.getLocation(8);
		Amenities amenities = amenitiesService.getAmenities(8);
		Properties property = new Properties(user, location, amenities, "condominium", "Condominium No.1", "Awesome place!", (float) 1999.00, created_datetime);
		
		service.saveProperty(property);
	    assertThat(Arrays.asList(types).contains(property.getType()));*/
	 }
	
	@Test
	public void testGetProperty() throws Exception{
		/*Properties property = service.getProperty(5);
		assertEquals(property.getId_property(), 5);*/
	}
	
	@Test
	public void testPutProperty() throws Exception{
		/*Properties property = service.getProperty(5);
		String original_name = property.getName();
		property.setName(property.getName()+" (updated2)");
		service.saveProperty(property);
		assertEquals(original_name+" (updated2)", property.getName());*/
	}

	@Test
	public void testDeleteProperty() throws Exception{
    
		/*Properties property = repo.findById(1).get();
	    repo.deleteById(user.getId_property());
	    Users deletedProperty = repo.findById(1).get();
	    assertThat(deletedProperty);*/

	    /*String uri = "http://localhost:8080/properties/7";
	    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
	    int status = mvcResult.getResponse().getStatus();
	    assertEquals(200, status);*/
	}
}
