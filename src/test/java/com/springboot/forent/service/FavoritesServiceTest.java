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
import com.springboot.forent.model.Favorites;
import com.springboot.forent.repository.FavoritesRepository;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@WebMvcTest(FavoritesService.class)
class FavoritesServiceTest {
	@Autowired
	FavoritesService service;
	
	@MockBean
	FavoritesRepository repo;
	
	@Test
	@DisplayName("TEST listAllUserFavorites")
	void listAllUserFavorites() throws Exception {
		Favorites fave1 = new Favorites(1,1,1);
		Favorites fave2 = new Favorites(2,2,1);
		Favorites fave3 = new Favorites(3,2,3);

		List<Favorites> list = new ArrayList<Favorites>();
		list.add(fave1);
		list.add(fave2);
		list.add(fave3);
		
		doReturn(list).when(repo).listAllUserFavorites(1);
		
		List<Favorites> returnedList = (List<Favorites>) service.listAllUserFavorites(1);

		Assertions.assertFalse(returnedList.isEmpty(), "No result.");
		Assertions.assertSame(returnedList.get(0), fave1, "User should be the same.");
		Assertions.assertEquals(returnedList.get(2).getId_property(), 2);
	}
	
	@Test
	@DisplayName("TEST getUserFavoriteProperty")
	void getUserFavoriteProperty() throws Exception{
		Favorites fave1 = new Favorites(3,2,3);
		
		doReturn(fave1).when(repo).findUserFavoriteProperty(3,2);
		
		Favorites fave = service.getUserFavoriteProperty(3,2);
		
		Assertions.assertEquals(fave.getId_favorite(),3);
		Assertions.assertEquals(fave.getId_property(), 2);
		Assertions.assertEquals(fave.getId_user(), 3);
	}
	
	@Test
	@DisplayName("TEST savePropertyToFavorites")
	void savePropertyToFavorites() throws Exception{
		Favorites fave1 = new Favorites(3,2,3);
		
		Integer savePropertyToFavoritesStatus = 1;
		
		doReturn(savePropertyToFavoritesStatus).when(repo).savePropertyToFavorites(fave1.getId_user(), 
				fave1.getId_property());	
		
		ResponseEntity<String> response = service.savePropertyToFavorites(fave1.getId_user(), fave1.getId_property());
		
		Assertions.assertEquals(response.getStatusCodeValue(), 201);
	}
	
	@Test
	@DisplayName("TEST deleteFavoriteProperty")
	void deleteFavoriteProperty() throws Exception{
		Integer updatePropertyToFavoritesStatus = 1;
		
		doReturn(updatePropertyToFavoritesStatus).when(repo).deleteFavoriteProperty(3, 2);
		
		ResponseEntity<String> response = service.deleteFavoriteProperty(3,2);
		
		Assertions.assertEquals(response.getStatusCodeValue(), 200);
	}
	
	@Test
	@DisplayName("TEST listAllUserFavoritesNORESULT")
	void listAllUserFavoritesNORESULT() throws NoDataFoundException {
		Assertions.assertThrows(NoDataFoundException.class, () -> {
			service.listAllUserFavorites(1);
		  });
	}
	
	@Test
	@DisplayName("TEST getUserFavoritePropertyNOTFOUND")
	void getUserFavoritePropertyNOTFOUND() throws DataNotFoundException {
		Assertions.assertThrows(DataNotFoundException.class, () -> {
			service.getUserFavoriteProperty(1,1);
		  });
	}
	
	@Test
	@DisplayName("TEST gsavePropertyToFavoritesNOTFOUND")
	void savePropertyToFavoritesNOTFOUND() throws DataNotFoundException {
		Assertions.assertThrows(DataNotFoundException.class, () -> {
			service.savePropertyToFavorites(1,1);
		  });
	}
	
	@Test
	@DisplayName("TEST deleteFavoritePropertyNOTFOUND")
	void deleteFavoritePropertyNOTFOUND() throws DataNotFoundException {
		Assertions.assertThrows(DataNotFoundException.class, () -> {
			service.deleteFavoriteProperty(1,1);
		  });
	}
}
