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

import com.springboot.forent.model.Amenities;
import com.springboot.forent.repository.AmenitiesRepository;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@WebMvcTest(AmenitiesService.class)
class AmenitiesServiceTest {
	@Autowired
	AmenitiesService service;
	
	@MockBean
	AmenitiesRepository repo;
	
	@Test
	@DisplayName("TEST getAmenitiesHasResult")
	void getAmenitiesHasResult() throws Exception {
		Amenities amenity1 = new Amenities(1, 1, 2, 1, 2, "Wifi, Cable");
		Amenities amenity2 = new Amenities(2, 2, 2, 2, 1, "Gym, Pool");
		Amenities amenity3 = new Amenities(3, 3, 1, 1, 1, "Microwave, Ref");

		List<Amenities> list = new ArrayList<Amenities>();
		list.add(amenity1);
		list.add(amenity2);
		list.add(amenity3);
		
		doReturn(list).when(repo).findAll();

		List<Amenities> returnedList = (List<Amenities>) service.listAllAmenities();

		Assertions.assertFalse(returnedList.isEmpty(), "No result.");
		Assertions.assertSame(returnedList.get(0), amenity1, "Amenity should be the same.");
		Assertions.assertEquals(returnedList.get(2).getRooms(), 1);
	}
	
	@Test
	@DisplayName("TEST getAmenityByID")
	void getAmenityByID() throws Exception{
		Amenities amenity1 = new Amenities(1, 1, 2, 1, 2, "Wifi, Cable");
		doReturn(Optional.of(amenity1)).when(repo).findById(1);
		
		Amenities amenity = service.getAmenities(1);
		
		Assertions.assertEquals(amenity.getId_amenity(),1);
		Assertions.assertEquals(amenity.getId_property(),1);
		Assertions.assertEquals(amenity.getRooms(),2);
		Assertions.assertEquals(amenity.getToilets(),1);
		Assertions.assertEquals(amenity.getBeds(),2);
		Assertions.assertEquals(amenity.getOther_amenities(),"Wifi, Cable");
	}
	
	@Test
	@DisplayName("TEST saveAmenity")
	void saveAmenity() throws Exception{
		Amenities amenity = new Amenities(1, 1, 2, 1, 2, "Wifi, Cable");
		doReturn(amenity).when(repo).save(amenity);
		
		Amenities addedAmenity = service.saveAmenities(amenity);
		
		Assertions.assertEquals(addedAmenity.getId_amenity(),1);
		Assertions.assertEquals(addedAmenity.getId_property(),1);
		Assertions.assertEquals(addedAmenity.getRooms(),2);
		Assertions.assertEquals(addedAmenity.getToilets(),1);
		Assertions.assertEquals(addedAmenity.getBeds(),2);
		Assertions.assertEquals(addedAmenity.getOther_amenities(),"Wifi, Cable");	
	}
}