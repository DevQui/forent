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

import com.springboot.forent.model.Favorites;
import com.springboot.forent.model.Reviews;
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
	@DisplayName("TEST getFavoritesHasResult")
	void getFavoritesHasResult() throws Exception {
		Favorites fave1 = new Favorites(1,1,1);
		Favorites fave2 = new Favorites(2,1,2);
		Favorites fave3 = new Favorites(3,2,3);

		List<Favorites> list = new ArrayList<Favorites>();
		list.add(fave1);
		list.add(fave2);
		list.add(fave3);
		
		doReturn(list).when(repo).findAll();
		
		List<Favorites> returnedList = (List<Favorites>) service.listAllFavorites();
		// Validate
		Assertions.assertFalse(returnedList.isEmpty(), "No result.");
		Assertions.assertSame(returnedList.get(0), fave1, "User should be the same.");
		Assertions.assertEquals(returnedList.get(2).getId_property(), 2);
	}
	
	@Test
	@DisplayName("TEST getFavoriteByID")
	void getFavoriteByID() throws Exception{
		Favorites fave1 = new Favorites(3,2,3);
		doReturn(Optional.of(fave1)).when(repo).findById(3);
		
		Favorites fave = service.getFavorite(3);
		
		Assertions.assertEquals(fave.getId_favorite(),3);
		Assertions.assertEquals(fave.getId_property(), 2);
		Assertions.assertEquals(fave.getId_user(), 3);
	}
	
	@Test
	@DisplayName("TEST saveFavorite")
	void saveFavorite() throws Exception{
		Favorites fave1 = new Favorites(3,2,3);
		doReturn(fave1).when(repo).save(fave1);
		
		Favorites addedFave = service.saveFavorite(fave1);
		
		Assertions.assertEquals(addedFave.getId_favorite(),3);
		Assertions.assertEquals(addedFave.getId_property(),2);
		Assertions.assertEquals(addedFave.getId_user(),3);	
	}
	
	@Test
	@DisplayName("TEST deleteFavorite")
	void deleteFavorite() throws Exception{
		Favorites fave = new Favorites(3,2,3);
				
		repo.delete(fave);
		String response = service.deleteFavorite(1);
		
		Assertions.assertEquals(response, "Favorite Property Deleted");
	}
}
