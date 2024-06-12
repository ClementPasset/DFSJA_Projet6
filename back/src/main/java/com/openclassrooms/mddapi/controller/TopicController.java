package com.openclassrooms.mddapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.Exception.BadRequestException;
import com.openclassrooms.mddapi.Exception.NotFoundException;
import com.openclassrooms.mddapi.service.ITopicService;

@RestController
@RequestMapping("/topic")
public class TopicController {

	private ITopicService topicService;

	public TopicController(ITopicService topicService) {
		this.topicService = topicService;
	}

	@GetMapping
	public ResponseEntity<?> getTopics() {
		return ResponseEntity.ok().body(topicService.getTopics());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getTopic(@PathVariable("id") String id) throws Exception {
		try {
			return ResponseEntity.ok().body(topicService.getTopic(id));
		} catch (BadRequestException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
