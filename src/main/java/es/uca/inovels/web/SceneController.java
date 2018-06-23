/**
 * Interactive Scenes: SceneController.java
 * Manages the behavior of the REST API for the Scene class
 */
package es.uca.inovels.web;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.uca.inovels.model.Constants;
import es.uca.inovels.model.Image;
import es.uca.inovels.model.Novel;
import es.uca.inovels.model.Scene;
import es.uca.inovels.repositories.NovelRepository;
import es.uca.inovels.repositories.SceneRepository;

/**
 * @author Francisco Fernández Sobejano
 *
 */

@RestController
public class SceneController {
	
	@Autowired
	SceneRepository sceneRepository;
	
	@Autowired
	NovelRepository novelRepository;

	@GetMapping("/api/scenes")
	public List<Scene> getAllScenes() {
		return sceneRepository.findAll();
	}
	
	@GetMapping("/api/scenes/{id}")
	public List<Scene> getScenes(@PathVariable long id) {
		Novel novelFinal;
		Optional<Novel> novel = novelRepository.findById(id);
		if (novel.isPresent()) {
			novelFinal = novel.get();
			return sceneRepository.findByNovel(novelFinal);	
		}
		return null;
	}
	
	@PostMapping("/api/scenes")
	public ResponseEntity<Object> createScene(@RequestBody Scene scene) {
		if (scene.getImage() == null) {
			scene.setImage(new Image (Constants.DEFAULT_SCENE, "defaultScene.jpg", "image/jpg", 800, 400));
		}
		Scene savedScene = sceneRepository.save(scene);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedScene.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/api/scenes/{id}")
	public ResponseEntity<Object> updateScene(@RequestBody Scene scene, @PathVariable long id) {
		Optional<Scene> sceneOptional = sceneRepository.findById(id);
		if (!sceneOptional.isPresent())
			return ResponseEntity.notFound().build();
		sceneOptional.get().setImage(scene.getImage());
		sceneOptional.get().setText(scene.getText());
		sceneOptional.get().setAnswer(scene.getAnswer());
		sceneRepository.save(sceneOptional.get());
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/api/scenes/{id}")
	public void deleteScene(@PathVariable long id) {
		sceneRepository.deleteById(id);
	}
}