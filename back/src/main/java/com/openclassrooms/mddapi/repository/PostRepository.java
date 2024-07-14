package com.openclassrooms.mddapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    /**
     * method to find all posts that belongs to the topics corresponding to the List of topic ids passed as a parameter
     * 
     * @param topicsId
     * @return List<Post>
     */
    public List<Post> findDistinctByTopicsIdIsIn(List<Long> topicsId);
}
