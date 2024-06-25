package com.openclassrooms.mddapi.DTO;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private Long id;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    private List<TopicDto> topics;

    private List<CommentDto> comments;

    private String author;
}
