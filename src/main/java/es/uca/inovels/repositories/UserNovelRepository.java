/**
 * Interactive Novels: UserNovelRepository.java
 */
package es.uca.inovels.repositories;

import es.uca.inovels.model.Novel;
import es.uca.inovels.model.User;
import es.uca.inovels.model.UserNovel;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Francisco Fernández Sobejano
 *
 */
@RepositoryRestResource(collectionResourceRel = "progress", path = "progress")
public interface UserNovelRepository extends JpaRepository<UserNovel, Long>{

	/**
	 * @param novel
	 * @return A list of UserNovels with that novel
	 */
	Optional<List<UserNovel>> findByNovel(Novel novel);
	
	/**
	 * @param user
	 * @return A list of UserNovels with that user
	 */
	Optional<List<UserNovel>> findByUser(User user);
	
	/**
	 * @param user
	 * @param novel
	 * @return The UserNovel with that user and novel
	 */
	Optional<UserNovel> findByUserAndNovel(User user, Novel novel);
	
}