package com.samisezgin.cache.redis.service.impl;

import java.util.List;

import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import com.samisezgin.cache.redis.model.Tutorial;
import com.samisezgin.cache.redis.repository.TutorialRepository;
import com.samisezgin.cache.redis.service.TutorialService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TutorialServiceImpl implements TutorialService {
	private final TutorialRepository tutorialRepository;

	@Override
	@Cacheable("tutorials")
	public List<Tutorial> getAllTutorials(String title) {

		if (title == null) {
			return tutorialRepository.findAll();
		} else {
			var opt = tutorialRepository.findByTitleContaining(title);
			return opt;
		}

	}

	@Override
	@Cacheable("tutorial")
	public Tutorial getTutorialById(Long id) {
		return tutorialRepository.findById(id).orElseThrow(() -> new RuntimeException("Tutorial not found"));
	}

	@Override
	@Cacheable("published_tutorials")
	public List<Tutorial> getByPublished() {
		return tutorialRepository.findByPublished(true);
	}

	@Override
	@Caching(evict = { @CacheEvict(value = { "tutorial", "tutorials", "published_tutorials" }, allEntries = true) })
	public Tutorial createTutorial(Tutorial tutorial) {
		Tutorial tempTutorial = tutorialRepository
				.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), tutorial.getPublished()));
		return tutorialRepository.save(tempTutorial);
	}

	@Override
	@Caching(evict = { @CacheEvict(value = { "tutorial", "tutorials", "published_tutorials" }, allEntries = true) })
	public Tutorial updateTutorial(Long id, Tutorial tutorial) {
		Tutorial tutorialData = tutorialRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Tutorial not found"));

		tutorialData.setTitle(tutorial.getTitle());
		tutorialData.setDescription(tutorial.getDescription());
		tutorialData.setPublished(tutorial.getPublished());
		return tutorialRepository.save(tutorialData);

	}

	@Override
	@Caching(evict = { @CacheEvict(value = { "tutorial", "tutorials", "published_tutorials" }, allEntries = true) })
	public void deleteTutorial(Long id) {
		if (tutorialRepository.existsById(id))
			tutorialRepository.deleteById(id);
		else
			throw new RuntimeException("Tutorial Not Found");
	}

	@Override
	@Caching(evict = { @CacheEvict(value = { "tutorial", "tutorials", "published_tutorials" }, allEntries = true) })
	public void deleteAllTutorials() {
		tutorialRepository.deleteAll();
	}

}
