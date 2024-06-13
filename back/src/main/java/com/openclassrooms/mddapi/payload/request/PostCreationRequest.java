package com.openclassrooms.mddapi.payload.request;

import java.util.List;

import lombok.Data;
import lombok.NonNull;

@Data
public class PostCreationRequest {
    @NonNull
    private String title;

    @NonNull
    private String content;

    @NonNull
    private List<Long> topics;
}
