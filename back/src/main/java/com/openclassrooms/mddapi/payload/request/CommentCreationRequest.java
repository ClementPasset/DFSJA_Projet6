package com.openclassrooms.mddapi.payload.request;

import lombok.Data;

@Data
public class CommentCreationRequest {
    private String content;

    private String postId;
}
