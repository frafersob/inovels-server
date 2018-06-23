/**
 * Interactive Novels: UserController.java
 * Manages the behavior of the REST API for the User class
 */
package es.uca.inovels.web;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.uca.inovels.model.Novel;
import es.uca.inovels.model.User;
import es.uca.inovels.model.UserNovel;
import es.uca.inovels.repositories.NovelRepository;
import es.uca.inovels.repositories.UserRepository;
import es.uca.inovels.services.NovelService;
import es.uca.inovels.services.UserNovelService;
import es.uca.inovels.services.UserService;

/**
 * @author Francisco Fernández Sobejano
 *
 */

@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	NovelRepository novelRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	NovelService novelService;
	
	@Autowired
	UserNovelService userNovelService;

	@GetMapping("/api/users")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	@GetMapping("/api/users/{id}")
	public User getUser(@PathVariable long id) {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) return null;
		return user.get();
	}
	
	@GetMapping("/api/userByName/{name}")
	public User getUser(@PathVariable String name) {
		return userRepository.findByUsername(name);
	}
	
	@RequestMapping(value="/api/signup", method = RequestMethod.POST)
	public User saveUser(@RequestBody User user){
		return userRepository.save(user);
	}
	
	@PostMapping("/api/users")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		User savedUser = userService.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@PostMapping("/api/progress/{userId}/{novelId}")
	public ResponseEntity<Object> updateProgress(@PathVariable("userId") long userId, 
			@PathVariable("novelId") long novelId) {
		Optional<User> userOptional = userRepository.findById(userId);
		Optional<Novel> novelOptional = novelRepository.findById(novelId);
		if (!userOptional.isPresent() || !novelOptional.isPresent())
			return ResponseEntity.notFound().build();
		
		userNovelService.turnPage(userOptional.get(), novelOptional.get());
		userRepository.save(userOptional.get());
		novelRepository.save(novelOptional.get());
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/api/progress/{userId}/{novelId}")
	public int getProgress(@PathVariable("userId") long userId, 
			@PathVariable("novelId") long novelId) {
		Optional<User> userOptional = userRepository.findById(userId);
		Optional<Novel> novelOptional = novelRepository.findById(novelId);
		if (!userOptional.isPresent() || !novelOptional.isPresent())
			return 0;
		return userNovelService.loadProgress(userOptional.get(), novelOptional.get());
	}
	
	//Returns progress per novel
	@GetMapping("/api/progress/{userId}")
	public Map<Long, Integer> getProgresses(@PathVariable("userId") long userId) {
		Optional<User> userOptional = userRepository.findById(userId);
		if (!userOptional.isPresent())
			return null;
		List<UserNovel> progress = userNovelService.loadUserNovelByUser(userOptional.get());
		Map<Long, Integer> map = new HashMap<Long, Integer>();
		for(UserNovel pair : progress) {
			map.put(pair.getNovelId(), pair.getPage());
		}
		return map;
	}
	
	@PutMapping("/api/users/{id}")
	public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable long id) {
		Optional<User> userOptional = userRepository.findById(id);
		if (!userOptional.isPresent())
			return ResponseEntity.notFound().build();
		userOptional.get().setAvatar(user.getAvatar());
		userOptional.get().setProgress(user.getProgress());
		userRepository.save(userOptional.get());
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/api/users/{id}")
	public void deleteUser(@PathVariable long id) {
		userRepository.deleteById(id);
	}
}