package com.openclassrooms.mddapi.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.DTO.PostDto;
import com.openclassrooms.mddapi.Exception.BadRequestException;
import com.openclassrooms.mddapi.Exception.NotFoundException;
import com.openclassrooms.mddapi.mapper.PostMapper;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.payload.request.PostCreationRequest;
import com.openclassrooms.mddapi.repository.PostRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class PostService implements IPostService {

	private PostRepository postRepository;

	private PostMapper mapper;

	private ITopicService topicService;

	private IUserService userService;

	@Override
	public PostDto createPost(PostCreationRequest request) throws NotFoundException, BadRequestException {
		Post post = new Post();
		post.setTitle(request.getTitle());
		post.setContent(request.getContent());
		request.getTopics().forEach(topicId -> {
			topicService.getTopic(topicId.toString()).addPost(post);
		});
		post.setAuthor(userService.getCurrentUser());

		return mapper.toDto(postRepository.save(post));
	}

	@Override
	public Post getPost(String id) throws Exception {
		try {
			Optional<Post> optionalPost = postRepository.findById(Long.valueOf(id));
			if (!optionalPost.isPresent()) {
				throw new NotFoundException("No post found with id '" + id + "'.");
			} else {
				return optionalPost.get();
			}
		} catch (NumberFormatException e) {
			throw new BadRequestException("Wrong format for Post id.");
		}
	}

	@Override
	public PostDto getPostDto(String id) throws Exception {
		return mapper.toDto(this.getPost(id));
	}

	@Override
	public List<Post> getPosts() {
		return postRepository.findAll();
	}

	@Override
	public List<PostDto> getPostsDto() {
		return mapper.toDto(this.getPosts());
	}

}
