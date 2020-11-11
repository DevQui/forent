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

import com.springboot.forent.model.Properties;
import com.springboot.forent.repository.PropertiesRepository;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@WebMvcTest(PropertiesService.class)
class PropertiesServiceTest {

	@Autowired
	PropertiesService service;
	
	@MockBean
	PropertiesRepository repo;
	
	@Test
	@DisplayName("TEST getUsersHasResult")
	void getUsersHasResult() throws Exception {
		Properties property1 = new Properties(1, "bungalow", "Bungalow Property", "A Bungalow Property", (float)3999.00, "2020-11-01 11:00:00");
		Properties property2 = new Properties(2, "apartment", "Apartment Property", "An Apartment Property", (float)1999.00, "2020-11-01 11:00:00");
		Properties property3 = new Properties(3, "condominium", "Condominium Property", "A Condominium Property", (float)2999.00, "2020-11-01 11:00:00");

		List<Properties> list = new ArrayList<Properties>();
		list.add(property1);
		list.add(property2);
		list.add(property3);
				
		doReturn(list).when(repo).findAll();

		List<Properties> returnedList = (List<Properties>) service.listAllProperties();

		Assertions.assertFalse(returnedList.isEmpty(), "No result.");
		Assertions.assertSame(returnedList.get(0), property1, "Property should be the same.");
		Assertions.assertEquals(returnedList.get(2).getPrice(), (float)2999.00);
	}
	
	@Test
	@DisplayName("TEST getUserByID")
	void getUserByID() throws Exception{
		Properties property1 = new Properties(1, "bungalow", "Bungalow Property", "A Bungalow Property", (float)3999.00, "2020-11-01 11:00:00");
		doReturn(Optional.of(property1)).when(repo).findById(1);
		
		Properties property = service.getProperty(1);
		
		Assertions.assertEquals(property.getId_property(),1);
		Assertions.assertEquals(property.getType(),"bungalow");
		Assertions.assertEquals(property.getName(),"Bungalow Property");
		Assertions.assertEquals(property.getDescription(),"A Bungalow Property");
		Assertions.assertEquals(property.getPrice(), (float)3999.00);
		Assertions.assertEquals(property.getUpdated_datetime(),"2020-11-01 11:00:00");
	}

}
