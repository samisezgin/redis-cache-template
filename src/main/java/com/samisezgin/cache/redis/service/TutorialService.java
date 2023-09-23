package com.samisezgin.cache.redis.service;

import java.util.List;

import com.samisezgin.cache.redis.model.Tutorial;

public interface TutorialService {

	List<Tutorial> getAllTutorials(String title);

	Tutorial getTutorialById(Long id);

	Tutorial createTutorial(Tutorial tutorial);

	Tutorial updateTutorial(Long id, Tutorial tutorial);

	void deleteTutorial(Long id);

	void deleteAllTutorials();

	List<Tutorial> getByPublished();

}
