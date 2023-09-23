package com.samisezgin.cache.redis.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samisezgin.cache.redis.model.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
	List<Tutorial> findByPublished(Boolean published);

	List<Tutorial> findByTitleContaining(String title);

	Optional<List<Tutorial>> findAllByTitle(String title);

}
