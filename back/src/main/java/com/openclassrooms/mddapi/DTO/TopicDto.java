package com.openclassrooms.mddapi.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicDto {
    private Long id;

    private String name;

    private String description;

    private boolean isSubscribed;
}
