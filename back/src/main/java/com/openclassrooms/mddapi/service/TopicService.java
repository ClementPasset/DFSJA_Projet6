package com.openclassrooms.mddapi.service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.DTO.TopicDto;
import com.openclassrooms.mddapi.Exception.BadRequestException;
import com.openclassrooms.mddapi.Exception.NotFoundException;
import com.openclassrooms.mddapi.mapper.TopicMapper;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.repository.TopicRepository;

@Service
@Transactional
public class TopicService implements ITopicService {

	private TopicRepository topicRepository;

	private TopicMapper mapper;

	public TopicService(TopicRepository topicRepository, TopicMapper mapper) {
		this.topicRepository = topicRepository;
		this.mapper = mapper;
	}


	/** 
	 * Returns the list of the existing Topics
	 * 
	 * @return List<TopicDto>
	 */
	@Override
	public List<TopicDto> getTopics() {
		return mapper.toDto(topicRepository.findAll());
	}


	/** 
	 * Returns the Topic corresponding to the id received as a parameter
	 * 
	 * @param id
	 * @return TopicDto
	 * @throws Exception
	 */
	@Override
	public TopicDto getTopic(String id) throws Exception {
		try {
			Topic topic = topicRepository.findById(Long.valueOf(id)).get();
			if (topic == null) {
				throw new NotFoundException("No topic found with id '" + id + "'.");
			}
			return mapper.toDto(topic);
		} catch (NumberFormatException e) {
			throw new BadRequestException("Wrong format for Topic id.");
		} catch (NoSuchElementException e) {
			throw new NotFoundException("No topic found with id '" + id + "'.");
		}
	}

}
