package com.openclassrooms.mddapi.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.openclassrooms.mddapi.DTO.PostDto;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.service.ITopicService;

@Component
public class PostMapper implements EntityMapper<Post, PostDto> {

    private TopicMapper topicMapper;

    private CommentMapper commentMapper;

    private ITopicService topicService;

    public PostMapper(TopicMapper topicMapper, ITopicService topicService, CommentMapper commentMapper) {
        this.topicMapper = topicMapper;
        this.topicService = topicService;
        this.commentMapper = commentMapper;
    }

    @Override
    public PostDto toDto(Post entity) {
        PostDto dto = new PostDto();
        dto.setContent(entity.getContent());
        dto.setTitle(entity.getTitle());
        if (entity.getAuthor() != null) {
            dto.setAuthor(entity.getAuthor().getUsername());
        }
        dto.setCreatedAt(entity.getCreatedAt());
        if (entity.getTopics() != null) {
            dto.setTopics(topicMapper.toDto(entity.getTopics()));
        }
        if (entity.getComments() != null) {
            dto.setComments(commentMapper.toDto(entity.getComments()));
        }
        dto.setId(entity.getId());

        return dto;
    }

    @Override
    public List<PostDto> toDto(List<Post> entityList) {
        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public Post toEntity(PostDto dto) {
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        if (dto.getTopics() != null) {
            dto.getTopics().forEach(topicId -> {
                try {
                    post.getTopics().add(topicMapper.toEntity(topicService.getTopicDto(topicId.toString())));
                } catch (Exception e) {
                    System.out.println("An error has occured : " + e.getMessage());
                }
            });
        }
        return post;
    }

    @Override
    public List<Post> toEntity(List<PostDto> dtoList) {
        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }

}
