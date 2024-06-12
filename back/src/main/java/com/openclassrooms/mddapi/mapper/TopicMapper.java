package com.openclassrooms.mddapi.mapper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.openclassrooms.mddapi.DTO.TopicDto;
import com.openclassrooms.mddapi.model.Topic;

public class TopicMapper {

    public Topic toEntity(TopicDto dto) {
        Topic topic = new Topic();
        topic.setId(dto.getId());
        topic.setName(dto.getName());

        return topic;
    }

    public List<Topic> toEntity(List<TopicDto> dtos) {
        return StreamSupport.stream(dtos.spliterator(), false).map(dto -> this.toEntity(dto))
                .collect(Collectors.toList());
    }

    public TopicDto toDto(Topic topic) {
        TopicDto dto = new TopicDto();
        dto.setId(topic.getId());
        dto.setName(topic.getName());

        return dto;
    }

    public List<TopicDto> toDto(List<Topic> topics) {
        return StreamSupport.stream(topics.spliterator(), false).map(topic -> this.toDto(topic))
                .collect(Collectors.toList());
    }

}
