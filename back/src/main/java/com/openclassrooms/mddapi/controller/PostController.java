package com.openclassrooms.mddapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.Exception.BadRequestException;
import com.openclassrooms.mddapi.Exception.NotFoundException;
import com.openclassrooms.mddapi.payload.request.PostCreationRequest;
import com.openclassrooms.mddapi.service.IPostService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/post")
public class PostController {

    private IPostService postService;

    public PostController(IPostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<?> getPosts() {
        return ResponseEntity.ok().body(postService.getPostsDto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPost(@PathVariable("id") String id) throws Exception {
        try {
            return ResponseEntity.ok().body(postService.getPostDto(id));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody PostCreationRequest request) throws Exception {
        try {
            return ResponseEntity.ok().body(postService.createPost(request));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/subscribed")
    public ResponseEntity<?> getSubscribedTopicsPosts() {
        return ResponseEntity.ok().body(postService.getSubscribedTopicPostsDto());
    }

}
