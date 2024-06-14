package com.openclassrooms.mddapi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.openclassrooms.mddapi.DTO.CommentDto;
import com.openclassrooms.mddapi.mapper.CommentMapper;
import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.payload.request.CommentCreationRequest;
import com.openclassrooms.mddapi.repository.CommentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class CommentService implements ICommentService {

    private IPostService postService;

    private IUserService userService;

    private CommentRepository commentRepository;

    private CommentMapper mapper;

    @Override
    public CommentDto createComment(CommentCreationRequest request) {
        Comment comment = new Comment();
        comment.setContent(request.getContent());
        comment.setPost(postService.getPost(request.getPostId()));
        comment.setAuthor(userService.getCurrentUser());

        return mapper.toDto(commentRepository.save(comment));
    }

}
