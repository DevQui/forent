package com.springboot.forent.service;

import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.forent.exception.DataNotFoundException;
import com.springboot.forent.exception.NoDataFoundException;
import com.springboot.forent.model.Location;
import com.springboot.forent.repository.LocationRepository;
import com.springboot.forent.repository.PropertiesRepository;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@WebMvcTest(LocationService.class)
class LocationServiceTest {
	/*@Autowired
	LocationService service;
	
	@MockBean
	PropertiesRepository propertiesRepository;
	
	@MockBean
	LocationRepository repo;
	
	@Test
	@DisplayName("TEST getLocationHasResult")
	void listAllLocation() throws Exception {
		Location loc1 = new Location(1,1,"Town1","City1","Region1","Country1");
		Location loc2 = new Location(2,2,"Town2","City2","Region2","Country2");
		Location loc3 = new Location(3,3,"Town3","City3","Region3","Country3");

		List<Location> list = new ArrayList<Location>();
		list.add(loc1);
		list.add(loc2);
		list.add(loc3);
		
		doReturn(list).when(repo).findAll();
		
		List<Location> returnedList = (List<Location>) service.listAllLocation();
		
		Assertions.assertFalse(returnedList.isEmpty(), "No result.");
		Assertions.assertSame(returnedList.get(0), loc1, "Location should be the same.");
		Assertions.assertEquals(returnedList.get(2).getId_property(), 3);
	}
	
	@Test
	@DisplayName("TEST getLocation")
	void getLocation() throws Exception{
		Location loc1 = new Location(1,1,"Town1","City1","Region1","Country1");
		doReturn(loc1).when(repo).getLocation(1);
		
		Location loc = service.getLocation(1);
		
		Assertions.assertEquals(loc.getId_location(),1);
		Assertions.assertEquals(loc.getId_property(),1);
		Assertions.assertEquals(loc.getTown(),"Town1");
		Assertions.assertEquals(loc.getCity(),"City1");
		Assertions.assertEquals(loc.getRegion(),"Region1");
		Assertions.assertEquals(loc.getCountry(),"Country1");
	}
		
	@Test
	@DisplayName("TEST getLocationByPropertyID")
	void getLocationByPropertyID() throws Exception{
		Location loc1 = new Location(1,1,"Town1","City1","Region1","Country1");
		doReturn(loc1).when(repo).getLocationByPropertyID(1);
		
		Location loc = service.getLocationByPropertyID(1);
		
		Assertions.assertEquals(loc.getId_location(),1);
		Assertions.assertEquals(loc.getId_property(),1);
		Assertions.assertEquals(loc.getTown(),"Town1");
		Assertions.assertEquals(loc.getCity(),"City1");
		Assertions.assertEquals(loc.getRegion(),"Region1");
		Assertions.assertEquals(loc.getCountry(),"Country1");
	}
	
	@Test
	@DisplayName("TEST getPropertyLocation")
	void getPropertyLocation() throws Exception{
		Location loc1 = new Location(1,1,"Town1","City1","Region1","Country1");
		doReturn(loc1).when(repo).findIdLocationFromProperty(1,1);
		
		Location loc = service.getPropertyLocation(1,1);
		
		Assertions.assertEquals(loc.getId_location(),1);
		Assertions.assertEquals(loc.getId_property(),1);
		Assertions.assertEquals(loc.getTown(),"Town1");
		Assertions.assertEquals(loc.getCity(),"City1");
		Assertions.assertEquals(loc.getRegion(),"Region1");
		Assertions.assertEquals(loc.getCountry(),"Country1");
	}
	
	@Test
	@DisplayName("TEST savePropertyLocation")
	void savePropertyLocation() throws Exception{
		Location loc = new Location(1,1,"Town1","City1","Region1","Country1");
		Integer savePropertyLocationStatus = 1;
		doReturn(savePropertyLocationStatus).when(repo).savePropertyLocation(loc.getId_property(), loc.getTown(),
				loc.getCity(), loc.getRegion(), loc.getCountry());
		
		ResponseEntity<String> response  = service.savePropertyLocation(1,loc);
		
		Assertions.assertEquals(response.getStatusCodeValue(), 201);
	}
	
	@Test
	@DisplayName("TEST updatePropertyLocation")
	void updatePropertyLocation() throws Exception{
		Location loc = new Location(1,1,"Town1","City1","Region1","Country1");
		Integer updatePropertyLocationStatus = 1;
		doReturn(updatePropertyLocationStatus).when(repo).updatePropertyLocation(loc.getId_location(), 
				loc.getId_property(), loc.getTown(), loc.getCity(), loc.getRegion(), loc.getCountry());
		
		ResponseEntity<String> response  = service.updatePropertyLocation(loc,1,1);
		
		Assertions.assertEquals(response.getStatusCodeValue(), 200);
	}
	
	@Test
	@DisplayName("TEST listAllLocationNORESULT")
	void listAllLocationNORESULT() throws NoDataFoundException {
		Assertions.assertThrows(NoDataFoundException.class, () -> {
			service.listAllLocation();
		  });
	}
	
	@Test
	@DisplayName("TEST getLocationNORESULT")
	void getLocationNORESULT() throws DataNotFoundException {
		Assertions.assertThrows(DataNotFoundException.class, () -> {
			service.getLocation(1);
		  });
	}
	
	@Test
	@DisplayName("TEST getLocationByPropertyIDNORESULT")
	void getLocationByPropertyIDNORESULT() throws DataNotFoundException {
		Assertions.assertThrows(DataNotFoundException.class, () -> {
			service.getLocationByPropertyID(1);
		  });
	}
	
	@Test
	@DisplayName("TEST getPropertyLocationNORESULT")
	void getPropertyLocationNORESULT() throws DataNotFoundException {
		Assertions.assertThrows(DataNotFoundException.class, () -> {
			service.getPropertyLocation(1,1);
		  });
	}
	
	@Test
	@DisplayName("TEST savePropertyLocationNORESULT")
	void savePropertyLocationNORESULT() throws DataNotFoundException {
		Assertions.assertThrows(DataNotFoundException.class, () -> {
			service.savePropertyLocation(1,new Location());
		  });
	}
	
	@Test
	@DisplayName("TEST updatePropertyLocationNORESULT")
	void updatePropertyLocationNORESULT() throws DataNotFoundException {
		Assertions.assertThrows(DataNotFoundException.class, () -> {
			service.updatePropertyLocation(new Location(),1,1);
		  });
	}*/
}
