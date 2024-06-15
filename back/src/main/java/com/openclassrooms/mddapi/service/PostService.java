package com.openclassrooms.mddapi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.DTO.PostDto;
import com.openclassrooms.mddapi.Exception.BadRequestException;
import com.openclassrooms.mddapi.Exception.NotFoundException;
import com.openclassrooms.mddapi.mapper.PostMapper;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
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

	/**
	 * create a new post
	 * 
	 * @param request
	 * @return PostDto
	 * @throws NotFoundException
	 * @throws BadRequestException
	 */
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

	/**
	 * retrieves a post with its id
	 * 
	 * @param id
	 * @return Post
	 * @throws NotFoundException
	 * @throws BadRequestException
	 */
	@Override
	public Post getPost(String id) throws NotFoundException, BadRequestException {
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

	/**
	 * retrieves a post with its id and returns it has a PostDto
	 * 
	 * @param id
	 * @return PostDto
	 * @throws Exception
	 */
	@Override
	public PostDto getPostDto(String id) throws Exception {
		return mapper.toDto(this.getPost(id));
	}

	/**
	 * returns all the posts
	 * 
	 * @return List<Post>
	 */
	@Override
	public List<Post> getPosts() {
		return postRepository.findAll();
	}

	/**
	 * return all the posts as PostDto
	 * 
	 * @return List<PostDto>
	 */
	@Override
	public List<PostDto> getPostsDto() {
		return mapper.toDto(this.getPosts());
	}

	
	/** 
	 * Get the Posts that belongs to the topics subscribed by the user. If the user subscribed to 0 topic, we return all the posts
	 * 
	 * @return List<Post>
	 */
	@Override
	public List<Post> getSubscribedTopicPosts() {
		User user = userService.getCurrentUser();
		List<Topic> subscribedToTopics = user.getTopics();
		if (subscribedToTopics.size() != 0) {
			List<Long> subscribedToTopicsId = subscribedToTopics.stream().map(topic -> topic.getId())
					.collect(Collectors.toList());
			List<Post> posts = postRepository.findDistinctByTopicsIdIsIn(subscribedToTopicsId);
			return posts;
		} else {
			return this.getPosts();
		}
	}

	
	/** 
	 * Get the Posts that belongs to the topics subscribed by the user, as DTOs. If the user subscribed to 0 topic, we return all the posts as DTOs
	 * 
	 * @return List<PostDto>
	 */
	@Override
	public List<PostDto> getSubscribedTopicPostsDto() {
		return mapper.toDto(this.getSubscribedTopicPosts());
	}

}
