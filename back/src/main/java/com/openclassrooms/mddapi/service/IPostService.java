package com.openclassrooms.mddapi.service;

import java.util.List;

import com.openclassrooms.mddapi.DTO.PostDto;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.payload.request.PostCreationRequest;

public interface IPostService {
    public List<Post> getPosts();

    public List<PostDto> getPostsDto();

    public Post getPost(String id) throws Exception;

    public PostDto getPostDto(String id) throws Exception;

    public PostDto createPost(PostCreationRequest request) throws Exception;
}
