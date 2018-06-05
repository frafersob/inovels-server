/**
 * Interactive Novels: UserNovelService.java
 */
package es.uca.inovels.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uca.inovels.model.Novel;
import es.uca.inovels.model.User;
import es.uca.inovels.model.UserNovel;
import es.uca.inovels.repositories.NovelRepository;
import es.uca.inovels.repositories.UserNovelRepository;
import es.uca.inovels.repositories.UserRepository;

/**
 * @author Francisco Fernández Sobejano
 *
 */

@Service
public class UserNovelService {
	
	@Autowired
	private UserNovelRepository repo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private NovelRepository novelRepo;

	public List<UserNovel> loadUserNovelByNovel(Novel novel){
		Optional<List<UserNovel>> users = repo.findByNovel(novel);
		if (users.isPresent()) {
			return users.get();
		} else {
			return null;
		}
	}
	
	public List<UserNovel> loadUserNovelByUser(User user){
		Optional<List<UserNovel>> novels = repo.findByUser(user);
		if (novels.isPresent()) {
			return novels.get();
		} else {
			return null;
		}
	}

	public UserNovel save(UserNovel userNovel) {
		return repo.save(userNovel);
	}

	public Optional<UserNovel> findOne(User user, Novel novel) {
		return repo.findByUserAndNovel(user, novel);
	}
	
	public int loadProgress(User user, Novel novel) {
		Optional<UserNovel> progress = findOne(user, novel);
		if (progress.isPresent()) {
			return progress.get().getPage();
		} else {
			return 0;
		}
	}
	
	public void turnPage(User user, Novel novel) {
		Optional<UserNovel> progress = findOne(user, novel);
		if (progress.isPresent()) {
			progress.get().turnPage();
			repo.save(progress.get());
			userRepo.save(user);
			novelRepo.save(novel);
		}
	}

	public void delete(UserNovel arg0) {
		repo.delete(arg0);
	}

	public List<UserNovel> findAll() {
		return repo.findAll();
	}
	
}