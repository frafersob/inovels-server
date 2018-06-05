/**
 * Interactive Novels: NovelService.java
 */
package es.uca.inovels.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uca.inovels.model.Novel;
import es.uca.inovels.repositories.NovelRepository;

/**
 * @author Francisco Fernández Sobejano
 *
 */

@Service
public class NovelService {
	
	@Autowired
	private NovelRepository repo;

	public Novel loadNovelByName(String novelname){

		return repo.findByName(novelname);
	}

	public Novel save(Novel novel) {
		return repo.save(novel);
	}

	public Optional<Novel> findOne(Long arg0) {
		return repo.findById(arg0);
	}

	public void delete(Novel arg0) {
		repo.delete(arg0);
	}

	public List<Novel> findAll() {
		return repo.findAll();
	}
	
}
