package com.openclassrooms.mddapi.payload.response;

import lombok.Data;

@Data
public class TokenResponse {

    public TokenResponse(String token) {
        setToken(token);
    }

    private String token;
}
