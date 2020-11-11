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

import com.springboot.forent.model.Location;
import com.springboot.forent.repository.LocationRepository;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@WebMvcTest(LocationService.class)
class LocationServiceTest {
	@Autowired
	LocationService service;
	
	@MockBean
	LocationRepository repo;
	
	@Test
	@DisplayName("TEST getLocationHasResult")
	void getLocationHasResult() throws Exception {
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
	@DisplayName("TEST getUserByID")
	void getUserByID() throws Exception{
		Location loc1 = new Location(1,1,"Town1","City1","Region1","Country1");
		doReturn(Optional.of(loc1)).when(repo).findById(1);
		
		Location loc = service.getLocation(1);
		
		Assertions.assertEquals(loc.getId_location(),1);
		Assertions.assertEquals(loc.getId_property(),1);
		Assertions.assertEquals(loc.getTown(),"Town1");
		Assertions.assertEquals(loc.getCity(),"City1");
		Assertions.assertEquals(loc.getRegion(),"Region1");
		Assertions.assertEquals(loc.getCountry(),"Country1");
	}
	
	@Test
	@DisplayName("TEST saveLocation")
	void saveLocatio() throws Exception{
		Location loc = new Location(1,1,"Town1","City1","Region1","Country1");
		doReturn(loc).when(repo).save(loc);
		
		Location addedLoc = service.saveLocation(loc);
		
		Assertions.assertEquals(addedLoc.getId_location(),1);
		Assertions.assertEquals(addedLoc.getId_property(),1);
		Assertions.assertEquals(addedLoc.getTown(),"Town1");
		Assertions.assertEquals(addedLoc.getCity(),"City1");
		Assertions.assertEquals(addedLoc.getRegion(),"Region1");
		Assertions.assertEquals(addedLoc.getCountry(),"Country1");	
	}
}
