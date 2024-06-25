package com.openclassrooms.mddapi.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.DTO.TopicDto;
import com.openclassrooms.mddapi.Exception.BadRequestException;
import com.openclassrooms.mddapi.Exception.NotFoundException;
import com.openclassrooms.mddapi.mapper.TopicMapper;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.TopicRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class TopicService implements ITopicService {

	private TopicRepository topicRepository;

	private IUserService userService;

	private TopicMapper mapper;

	/**
	 * Returns the list of the existing Topics
	 * 
	 * @return List<Topic>
	 */
	@Override
	public List<Topic> getTopics() {
		return topicRepository.findAll();
	}

	/**
	 * Returns the list of the existing Topics as Dto
	 * 
	 * @return List<TopicDto>
	 */
	@Override
	public List<TopicDto> getTopicsDto() {
		return mapper.toDto(this.getTopics());
	}

	/**
	 * Returns the Topic corresponding to the id received as a parameter
	 * 
	 * @param id
	 * @return Topic
	 * @throws Exception
	 */
	@Override
	public Topic getTopic(String id) throws BadRequestException, NotFoundException {
		try {
			Topic topic = topicRepository.findById(Long.valueOf(id)).get();
			if (topic == null) {
				throw new NotFoundException("No topic found with id '" + id + "'.");
			}
			return topic;
		} catch (NumberFormatException e) {
			throw new BadRequestException("Wrong format for Topic id.");
		} catch (NoSuchElementException e) {
			throw new NotFoundException("No topic found with id '" + id + "'.");
		}
	}

	/**
	 * Returns the TopicDto corresponding to the id received as a parameter
	 * 
	 * @param id
	 * @return TopicDto
	 * @throws Exception
	 */
	@Override
	public TopicDto getTopicDto(String id) throws Exception {
		return mapper.toDto(this.getTopic(id));
	}

	/**
	 * subscribes the user to the topic if it is not already the case
	 * 
	 * @param topicId
	 * @param userId
	 * @throws Exception
	 */
	@Override
	public void subscribeToTopic(String topicId) throws Exception {
		try {
			Optional<Topic> optionalTopic = topicRepository.findById(Long.valueOf(topicId));
			User currentUser = userService.getCurrentUser();
			if (!optionalTopic.isPresent()) {
				throw new NotFoundException("The topic couldn't be found.");
			} else {
				Topic topic = optionalTopic.get();
				if (!topic.getUsers().contains(currentUser)) {
					topic.getUsers().add(currentUser);
					topicRepository.save(topic);
				} else {
					throw new BadRequestException("User already subscribed to this topic.");
				}
			}
		} catch (NumberFormatException e) {
			throw new BadRequestException("Error with the parameters' format.");
		}
	}

	/**
	 * unsubscribe the user to the topic, if the user is subscribed
	 * 
	 * @param topicId
	 * @param userId
	 * @throws Exception
	 */
	@Override
	public void unSubscribeToTopic(String topicId) throws Exception {
		try {
			Optional<Topic> optionalTopic = topicRepository.findById(Long.valueOf(topicId));
			User currentUser = userService.getCurrentUser();
			if (!optionalTopic.isPresent()) {
				throw new NotFoundException("The topic couldn't be found.");
			} else {
				Topic topic = optionalTopic.get();
				if (topic.getUsers().contains(currentUser)) {
					topic.getUsers().remove(currentUser);
					topicRepository.save(topic);
				} else {
					throw new BadRequestException("User is not subscribed to this topic.");
				}
			}
		} catch (NumberFormatException e) {
			throw new BadRequestException("Error with the parameters' format.");
		}
	}

}
