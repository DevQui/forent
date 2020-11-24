package com.springboot.forent.service;


import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.forent.exception.DataNotFoundException;
import com.springboot.forent.exception.NoDataFoundException;
import com.springboot.forent.model.Properties;
import com.springboot.forent.repository.PropertiesRepository;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@WebMvcTest(PropertiesService.class)
class PropertiesServiceTest{

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
				
		doReturn(list).when(repo).findAllWithUserInfo();

		List<Properties> returnedList = (List<Properties>) service.listAllProperties();

		Assertions.assertFalse(returnedList.isEmpty(), "No result.");
		Assertions.assertSame(returnedList.get(0), property1, "Property should be the same.");
		Assertions.assertEquals(returnedList.get(2).getPrice(), (float)2999.00);
	}
	
	@Test
	@DisplayName("TEST getUserByIDFOUND")
	void getUserByIDFOUND() throws Exception{
		Properties property1 = new Properties(1, "bungalow", "Bungalow Property", "A Bungalow Property", (float)3999.00, "2020-11-01 11:00:00");
		doReturn(Optional.of(property1)).when(repo).findById(1);
		
		ResponseEntity<Properties> property = service.getProperty(1);
		
		Assertions.assertEquals(property.getBody().getId_property(),1);
		Assertions.assertEquals(property.getBody().getType(),"bungalow");
		Assertions.assertEquals(property.getBody().getName(),"Bungalow Property");
		Assertions.assertEquals(property.getBody().getDescription(),"A Bungalow Property");
		Assertions.assertEquals(property.getBody().getPrice(), (float)3999.00);
		Assertions.assertEquals(property.getBody().getUpdated_datetime(),"2020-11-01 11:00:00");
	}
	
	@Test
	@DisplayName("TEST getPropertyOfUserFOUND")
	void getPropertyOfUserFOUND() throws Exception{
		Properties property1 = new Properties(1, 1, "bungalow", "Bungalow Property", "A Bungalow Property", (float)3999.00, "2020-11-01 11:00:00", "2020-11-01 11:00:00");
		
		doReturn(property1).when(repo).findPropertyOfUser(1,1);
		
		ResponseEntity<Properties> property = service.getPropertyOfUser(1,1);
		
		Assertions.assertEquals(property.getBody().getId_property(),1);
		Assertions.assertEquals(property.getBody().getType(),"bungalow");
		Assertions.assertEquals(property.getBody().getName(),"Bungalow Property");
		Assertions.assertEquals(property.getBody().getDescription(),"A Bungalow Property");
		Assertions.assertEquals(property.getBody().getPrice(), (float)3999.00);
		Assertions.assertEquals(property.getBody().getUpdated_datetime(),"2020-11-01 11:00:00");
	}
	
	@Test
	@DisplayName("TEST getUserPropertiesFOUND")
	void getUserPropertiesFOUND() throws Exception{
		
		Properties property1 = new Properties(1, 1, "bungalow", "Bungalow Property", "A Bungalow Property", (float)3999.00, "2020-11-01 11:00:00", "2020-11-01 11:00:00");
		Properties property2 = new Properties(2, 1, "bungalow", "Bungalow Property2", "A Bungalow Property2", (float)3999.00, "2020-11-01 11:00:00", "2020-11-01 11:00:00");
		
		List<Properties> list = new ArrayList<>();
		list.add(property1);
		list.add(property2);
		
		doReturn(list).when(repo).getUserListOfProperties(1);
		
		List<Properties> property = service.getUserProperties(1);

		Assertions.assertEquals(property.get(0).getId_property(),1);
		Assertions.assertEquals(property.get(0).getType(),"bungalow");
		Assertions.assertEquals(property.get(0).getName(),"Bungalow Property");
		Assertions.assertEquals(property.get(0).getDescription(),"A Bungalow Property");
		Assertions.assertEquals(property.get(0).getPrice(), (float)3999.00);
		Assertions.assertEquals(property.get(0).getUpdated_datetime(),"2020-11-01 11:00:00");

		Assertions.assertEquals(property.get(1).getId_property(),2);
		Assertions.assertEquals(property.get(1).getType(),"bungalow");
		Assertions.assertEquals(property.get(1).getName(),"Bungalow Property2");
		Assertions.assertEquals(property.get(1).getDescription(),"A Bungalow Property2");
		Assertions.assertEquals(property.get(1).getPrice(), (float)3999.00);
		Assertions.assertEquals(property.get(1).getUpdated_datetime(),"2020-11-01 11:00:00");

	}	
	
	@Test
	@DisplayName("TEST addPropertySUCCESS")
	void addPropertySUCCESS() throws Exception{
		Properties addedProperty = new Properties(1, 1, "bungalow", "Bungalow Property", "A Bungalow Property", (float)3999.00, "2020-11-01 11:00:00", "2020-11-01 11:00:00");
		Integer user_id = 1;
		Integer addedPropertyStatus = 1;
		
		doReturn(addedPropertyStatus).when(repo).saveProperty(Mockito.anyInt(), Mockito.anyString(),
				Mockito.anyString(), Mockito.anyString(), Mockito.anyFloat(), Mockito.anyString());
		
		ResponseEntity<String> property = service.addProperty(user_id, addedProperty);

		Assertions.assertEquals(property.getStatusCode().value(),201);
	}
	
	@Test
	@DisplayName("TEST updateProperty")
	void updateProperty() throws Exception{
		Properties updateProperty = new Properties(1, 1, "bungalow", "Bungalow Property2", "A Bungalow Property2", (float)3999.00, "2020-11-01 11:00:00", "2020-11-01 11:00:00");
		Properties originalProperty = new Properties(1, "bungalow", "Bungalow Property2", "A Bungalow Property2", (float)3999.00, "2020-11-01 11:00:00");
		Integer updatePropertyStatus = 1;
		
		Mockito.when(repo.updateProperty(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString(),
				Mockito.anyString(), Mockito.anyString(), Mockito.anyFloat(), 
				Mockito.anyString())).thenReturn(updatePropertyStatus);
		
		ResponseEntity<String> property = service.updateProperty(updateProperty.getUsers_id_user(),
				updateProperty.getId_property(), originalProperty);
		
		Assertions.assertEquals(property.getStatusCode().value(), 200);
	}
	
	
	@Test
	@DisplayName("TEST deletePropertySUCCESS")
	void deletePropertySUCCESS() throws Exception{		
		doReturn(1).when(repo).deletePropertyOfUser(1,1);
		
		ResponseEntity<String> property = service.deleteProperty(1,1);
		Assertions.assertEquals(property.getBody(),"Property Deleted");
	}
	
	
	@Test
	@DisplayName("TEST findAllWithUserInfoNORESULT")
	void findAllWithUserInfoNORESULT() throws NoDataFoundException {
		Assertions.assertThrows(NoDataFoundException.class, () -> {
			service.listAllProperties();
		  });
	}
	
	@Test
	@DisplayName("TEST getUserPropertiesNOTFOUND")
	void getUserPropertiesNOTFOUND() throws DataNotFoundException {
		Assertions.assertThrows(DataNotFoundException.class, () -> {
			service.getUserProperties(3);
		  });
	}
	
	@Test
	@DisplayName("TEST getPropertyOfUserNOTFOUND")
	void getPropertyOfUserNOTFOUND() throws DataNotFoundException {
		Assertions.assertThrows(DataNotFoundException.class, () -> {
			service.getPropertyOfUser(1,2);
		  });
	}
	
	@Test
	@DisplayName("TEST getPropertyNOTFOUND")
	void getPropertyNOTFOUND() throws DataNotFoundException {
		Assertions.assertThrows(DataNotFoundException.class, () -> {
			service.getProperty(3);
		  });
	}
	
	@Test
	@DisplayName("TEST updatePropertyNOTFOUND")
	void addPropertyNOTFOUND() throws DataNotFoundException {
		Properties addedProperty = new Properties(1, 1, "bungalow", "Bungalow Property", "A Bungalow Property", (float)3999.00, "2020-11-01 11:00:00", "2020-11-01 11:00:00");
		Assertions.assertThrows(DataNotFoundException.class, () -> {
			service.addProperty(3,addedProperty);
		  });
	}
	
	@Test
	@DisplayName("TEST updatePropertyNOTFOUND")
	void updatePropertyNOTFOUND() throws DataNotFoundException {
		Properties addedProperty = new Properties(1, 1, "bungalow", "Bungalow Property", "A Bungalow Property", (float)3999.00, "2020-11-01 11:00:00", "2020-11-01 11:00:00");
		Assertions.assertThrows(DataNotFoundException.class, () -> {
			service.updateProperty(3,3,addedProperty);
		  });
	}
	
	@Test
	@DisplayName("TEST deletePropertyNOTFOUND")
	void deletePropertyNOTFOUND() throws DataNotFoundException {
		Assertions.assertThrows(DataNotFoundException.class, () -> {
			service.deleteProperty(3,3);
		  });
	}
}
