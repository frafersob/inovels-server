/**
 * Interactive Novels: NovelRepository.java
 */
package es.uca.inovels.repositories;

import es.uca.inovels.model.Novel;
import es.uca.inovels.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Francisco Fernández Sobejano
 *
 */

@RepositoryRestResource(collectionResourceRel = "novels", path = "novels")
public interface NovelRepository extends JpaRepository<Novel, Long>{

	/**
	 * @param novelname
	 * @return A novel with that name
	 */
	Novel findByName(String novelname);
	
}