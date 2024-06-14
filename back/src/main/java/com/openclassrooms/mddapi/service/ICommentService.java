package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.DTO.CommentDto;
import com.openclassrooms.mddapi.payload.request.CommentCreationRequest;

public interface ICommentService {
    public CommentDto createComment(CommentCreationRequest request);
}
