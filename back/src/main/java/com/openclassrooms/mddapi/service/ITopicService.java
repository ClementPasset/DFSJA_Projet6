package com.openclassrooms.mddapi.service;

import java.util.List;

import com.openclassrooms.mddapi.DTO.TopicDto;

public interface ITopicService {

	List<TopicDto> getTopics();

	TopicDto createTopic(TopicDto topicDto);

	TopicDto updateTopic(TopicDto topicDto);

	void deleteTopic(TopicDto topicDto);
}
