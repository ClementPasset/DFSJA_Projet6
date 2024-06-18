package com.openclassrooms.mddapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.Exception.BadRequestException;
import com.openclassrooms.mddapi.Exception.NotFoundException;
import com.openclassrooms.mddapi.service.ITopicService;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/topic")
@CrossOrigin(origins = "http://localhost:4200")
public class TopicController {

	private ITopicService topicService;

	public TopicController(ITopicService topicService) {
		this.topicService = topicService;
	}

	@GetMapping
	public ResponseEntity<?> getTopics() {
		return ResponseEntity.ok().body(topicService.getTopicsDto());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getTopic(@PathVariable("id") String id) throws Exception {
		try {
			return ResponseEntity.ok().body(topicService.getTopicDto(id));
		} catch (BadRequestException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/{topicId}/subscribe/{userId}")
	public ResponseEntity<?> subscribeToTopic(@PathVariable("topicId") String topicId,
			@PathVariable("userId") String userId) throws Exception {
		try {
			topicService.subscribeToTopic(topicId, userId);
			return ResponseEntity.ok().build();
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (BadRequestException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/{topicId}/subscribe/{userId}")
	public ResponseEntity<?> unSubscribeToTopic(@PathVariable("topicId") String topicId,
			@PathVariable("userId") String userId) throws Exception {
		try {
			topicService.unSubscribeToTopic(topicId, userId);
			return ResponseEntity.ok().build();
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (BadRequestException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
