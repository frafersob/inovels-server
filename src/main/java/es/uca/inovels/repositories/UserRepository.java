/**
 * Interactive Novels: UserRepository.java
 */
package es.uca.inovels.repositories;

import es.uca.inovels.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Francisco Fernández Sobejano
 *
 */
@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends JpaRepository<User, Long>{
	
	public User findByUsername(String username);
}