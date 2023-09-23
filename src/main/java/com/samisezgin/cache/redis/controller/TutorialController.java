package com.samisezgin.cache.redis.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.samisezgin.cache.redis.model.Tutorial;
import com.samisezgin.cache.redis.service.TutorialService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TutorialController {

	private final TutorialService tutorialService;

	@GetMapping("/tutorials")
	public ResponseEntity<?> getAllTutorials(@RequestParam(required = false) String title) {
		try {
			return new ResponseEntity<>(tutorialService.getAllTutorials(title), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/tutorials/{id}")
	public ResponseEntity<?> getTutorialById(@PathVariable("id") long id) {
		try {
			return new ResponseEntity<>(tutorialService.getTutorialById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/tutorials")
	public ResponseEntity<?> createTutorial(@RequestBody Tutorial tutorial) {
		try {
			return new ResponseEntity<>(tutorialService.createTutorial(tutorial), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("/tutorials/{id}")
	public ResponseEntity<?> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {

		try {
			return new ResponseEntity<>(tutorialService.updateTutorial(id, tutorial), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping("/tutorials/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
		try {
			tutorialService.deleteTutorial(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/tutorials")
	public ResponseEntity<?> deleteAllTutorials() {
		try {
			tutorialService.deleteAllTutorials();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/tutorials/published")
	public ResponseEntity<?> findByPublished() {
		try {
			return new ResponseEntity<>(tutorialService.getByPublished(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
