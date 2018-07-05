package es.uca.inovels.repositories;

import es.uca.inovels.model.Novel;
import es.uca.inovels.model.Scene;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SceneRepository extends JpaRepository<Scene, Long>, JpaSpecificationExecutor<Scene> {
	/**
	 * @param novel
	 * @return The scenes of the novel we asked for
	 */
	List<Scene> findByNovel(Novel novel);
}
