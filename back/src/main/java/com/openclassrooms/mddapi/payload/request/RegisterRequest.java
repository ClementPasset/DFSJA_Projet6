package com.openclassrooms.mddapi.payload.request;

import lombok.Data;
import lombok.NonNull;

@Data
public class RegisterRequest {
    @NonNull
    private String username;

    @NonNull
    private String email;

    @NonNull
    private String password;
}
