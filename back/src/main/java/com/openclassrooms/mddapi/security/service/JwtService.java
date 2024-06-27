package com.openclassrooms.mddapi.security.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.payload.response.TokenResponse;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class JwtService {
    private JwtEncoder jwtEncoder;

    public TokenResponse generateToken(Authentication auth) {
        Instant now = Instant.now();
        Instant expiresAt = now.plus(1, ChronoUnit.DAYS);
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuedAt(now)
                .expiresAt(expiresAt)
                .subject(auth.getName())
                .build();
        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters
                .from(JwsHeader.with(MacAlgorithm.HS256).build(), claims);
        return new TokenResponse(this.jwtEncoder.encode(jwtEncoderParameters).getTokenValue(), expiresAt);
    }

}
