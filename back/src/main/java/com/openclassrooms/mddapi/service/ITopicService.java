package com.openclassrooms.mddapi.service;

import java.util.List;

import com.openclassrooms.mddapi.DTO.TopicDto;
import com.openclassrooms.mddapi.model.Topic;

public interface ITopicService {

	List<Topic> getTopics();

	List<TopicDto> getTopicsDto();

	Topic getTopic(String id) throws Exception;

	TopicDto getTopicDto(String id) throws Exception;

	void subscribeToTopic(String topicId, String userId) throws Exception;

	void unSubscribeToTopic(String topicId, String userId) throws Exception;
}
