/**
 * Interactive Novels: UserController.java
 * Manages the behavior of the REST API for the User class
 */
package es.uca.inovels.web;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.uca.inovels.model.User;
import es.uca.inovels.repositories.UserRepository;
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
	UserService userService;

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
	
	@PutMapping("/api/users/{id}")
	public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable long id) {
		Optional<User> userOptional = userRepository.findById(id);
		if (!userOptional.isPresent())
			return ResponseEntity.notFound().build();
		userOptional.get().setAvatar(user.getAvatar());
		userRepository.save(userOptional.get());
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/api/users/{id}")
	public void deleteUser(@PathVariable long id) {
		userRepository.deleteById(id);
	}
}