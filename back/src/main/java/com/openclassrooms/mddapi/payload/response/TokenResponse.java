package com.openclassrooms.mddapi.payload.response;

import java.time.Instant;

import lombok.Data;

@Data
public class TokenResponse {

    public TokenResponse(String token, Instant expirationDate) {
        setToken(token);
        setExpirationDate(expirationDate);
    }

    private String token;

    private String email;

    private String username;

    private Instant expirationDate;
}
