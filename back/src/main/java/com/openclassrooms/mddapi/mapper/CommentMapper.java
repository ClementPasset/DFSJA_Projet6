package com.openclassrooms.mddapi.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.openclassrooms.mddapi.DTO.CommentDto;
import com.openclassrooms.mddapi.DTO.UserDto;
import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.service.IUserService;

@Component
public class CommentMapper implements EntityMapper<Comment, CommentDto> {

    private IUserService userService;

    @Override
    public CommentDto toDto(Comment entity) {
        CommentDto dto = new CommentDto();
        dto.setContent(entity.getContent());
        dto.setAuthor(new UserDto(entity.getAuthor().getId(), entity.getAuthor().getUsername()));
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setId(entity.getId());
        return dto;
    }

    @Override
    public List<CommentDto> toDto(List<Comment> entityList) {
        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public Comment toEntity(CommentDto dto) {
        Comment comment = new Comment();
        comment.setAuthor(userService.getUser(dto.getAuthor().getId()));
        comment.setContent(dto.getContent());
        comment.setCreatedAt(dto.getCreatedAt());
        comment.setId(dto.getId());
        return comment;
    }

    @Override
    public List<Comment> toEntity(List<CommentDto> dtoList) {
        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }

}
