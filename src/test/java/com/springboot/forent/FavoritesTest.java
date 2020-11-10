package com.springboot.forent;


import static org.junit.Assert.assertEquals;

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

import com.springboot.forent.service.FavoritesService;
import com.springboot.forent.service.PropertiesService;
import com.springboot.forent.service.UsersService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ForentApplication.class)
@AutoConfigureMockMvc
class FavoritesTest {
	
	@Autowired
	protected MockMvc mvc;
	
	@Autowired
	FavoritesService service;
	
	@Autowired
	PropertiesService propertiesService;
	
	@Autowired
	UsersService usersService;

	@Test
	void test() {
		//fail("Not yet implemented");
	}
	
	@Test
	@Rollback(false)
	public void testPostFavorite() throws Exception {
		
		/*Users user = usersService.getUser(13);
		Favorites favorite = new Favorites(2,user);
		
		service.saveFavorite(favorite);
	    assertEquals(favorite.getId_property(),2);*/
	}
	
	@Test
	public void testGetFavorite() throws Exception{
		/*Favorites favorite = service.getFavorite(1);
		assertEquals(favorite.getId_favorite(), 1);*/
	}
	

	@Test
	public void testDeleteFavorites() throws Exception{
	    /*String uri = "http://localhost:8080/favorites/6";
	    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
	    int status = mvcResult.getResponse().getStatus();
	    assertEquals(200, status);*/
	}

}
