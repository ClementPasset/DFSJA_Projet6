package com.openclassrooms.mddapi.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.openclassrooms.mddapi.DTO.TopicDto;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.Topic;

@Component
public class TopicMapper implements EntityMapper<Topic, TopicDto> {

    public Topic toEntity(TopicDto dto) {
        Topic topic = new Topic();
        topic.setId(dto.getId());
        topic.setName(dto.getName());

        return topic;
    }

    public List<Topic> toEntity(List<TopicDto> dtos) {
        return dtos.stream().map(this::toEntity).collect(Collectors.toList());
    }

    public TopicDto toDto(Topic topic) {
        TopicDto dto = new TopicDto();
        dto.setId(topic.getId());
        dto.setName(topic.getName());
        dto.setPosts(topic.getPosts().stream().map(Post::getId).collect(Collectors.toList()));

        return dto;
    }

    public List<TopicDto> toDto(List<Topic> topics) {
        return topics.stream().map(this::toDto).collect(Collectors.toList());
    }

}
