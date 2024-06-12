package com.openclassrooms.mddapi.service;

import java.util.List;

import com.openclassrooms.mddapi.DTO.TopicDto;

public interface ITopicService {

	List<TopicDto> getTopics();

	TopicDto getTopic(String id) throws Exception;

	TopicDto createTopic(TopicDto topicDto) throws Exception;

	TopicDto updateTopic(TopicDto topicDto);

	void deleteTopic(TopicDto topicDto);
}
