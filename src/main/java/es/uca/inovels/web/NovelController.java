/**
 * Interactive Novels: NovelController.java
 * Manages the behavior of the REST API for the Novel class
 */
package es.uca.inovels.web;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.uca.inovels.model.Novel;
import es.uca.inovels.repositories.NovelRepository;

/**
 * @author Francisco Fernández Sobejano
 *
 */

@RestController
public class NovelController {
	
	@Autowired
	NovelRepository novelRepository;

	@GetMapping("/api/novels")
	public List<Novel> getAllNovels() {
		return novelRepository.findAll();
	}
	
	@GetMapping("/api/novels/{id}")
	public Novel getNovel(@PathVariable long id) {
		Optional<Novel> novel = novelRepository.findById(id);
		if(!novel.isPresent()) return null;
		return novel.get();
	}
	
	@GetMapping("/api/novelsByIds/{ids}")
	public List<Novel> getNovelsByIds(@PathVariable List<Long> ids) {
		Optional<List<Novel>> novels = novelRepository.findByIdIn(ids);
		if(!novels.isPresent()) return null;
		return novels.get();
	}
	
	@PostMapping("/api/novels")
	public ResponseEntity<Object> createNovel(@RequestBody Novel novel) {
		Novel savedNovel = novelRepository.save(novel);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedNovel.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/api/novels/{id}")
	public ResponseEntity<Object> updateNovel(@RequestBody Novel novel, @PathVariable long id) {
		Optional<Novel> novelOptional = novelRepository.findById(id);
		if (!novelOptional.isPresent())
			return ResponseEntity.notFound().build();
		novelOptional.get().setName(novel.getName());
		novelOptional.get().setImage(novel.getImage());
		novelOptional.get().setDescription(novel.getDescription());
		novelRepository.save(novelOptional.get());
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/api/novels/{id}")
	public void deleteNovel(@PathVariable long id) {
		novelRepository.deleteById(id);
	}
}