package com.openclassrooms.mddapi.DTO;

import lombok.Data;

@Data
public class UserDto {

    public UserDto(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    private Long id;

    private String username;
}
