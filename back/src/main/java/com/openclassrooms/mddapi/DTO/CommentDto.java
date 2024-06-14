package com.openclassrooms.mddapi.DTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CommentDto {
    private Long id;

    private String content;

    private Long authorId;

    private LocalDateTime createdAt;
}
