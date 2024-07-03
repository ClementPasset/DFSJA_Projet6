package com.openclassrooms.mddapi.payload.request;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String Username;
    private String email;
}
