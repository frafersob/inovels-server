/**
 * Interactive Scenes: SceneService.java
 */
package es.uca.inovels.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uca.inovels.model.Novel;
import es.uca.inovels.model.Scene;
import es.uca.inovels.repositories.SceneRepository;

/**
 * @author Francisco Fernández Sobejano
 *
 */

@Service
public class SceneService {
	
	@Autowired
	private SceneRepository repo;

	public List<Scene> loadScenesByNovel(Novel novel){
		return repo.findByNovel(novel);
	}

	public Scene save(Scene scene) {
		return repo.save(scene);
	}

	public Optional<Scene> findOne(Long arg0) {
		return repo.findById(arg0);
	}

	public void delete(Scene arg0) {
		repo.delete(arg0);
	}

	public List<Scene> findAll() {
		return repo.findAll();
	}
	
}
