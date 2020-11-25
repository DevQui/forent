package com.springboot.forent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.forent.model.Favorites;

@Repository
public interface FavoritesRepository extends CrudRepository<Favorites, Integer>{	
	@Query("SELECT fave FROM Favorites fave WHERE fave.id_user = ?1")
	List<Favorites> listAllUserFavorites(Integer id_user);

	@Query("SELECT fave FROM Favorites fave WHERE fave.id_user = ?1 AND fave.id_favorite = ?2")
	Favorites findUserFavoriteProperty(Integer id_user, Integer id_favorite);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("DELETE FROM Favorites fave WHERE fave.id_user = ?1 AND fave.id_favorite = ?2")
	Integer deleteFavoriteProperty(Integer id_user, Integer id);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "INSERT INTO favorites(id_user, id_property) " +
			"SELECT ?1, ?2 FROM users " + 
			"WHERE EXISTS(SELECT id_user FROM users WHERE id_user = ?1) AND "+
			"EXISTS(SELECT id_property FROM properties WHERE id_property = ?2) LIMIT 1")
	Integer savePropertyToFavorites(Integer id_user, Integer id_property);
}
