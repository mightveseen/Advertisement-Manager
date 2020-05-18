package com.senlainc.gitcourses.javatraining.petushokvaliantsin.configuration.filter;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.senlainc.gitcourses.javatraining.petushokvaliantsin.dto.SystemUserDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

public class TokenMapper {

    private final String tokenPrefix;
    private final String secretKey;
    private final String tokenHeader;

    public TokenMapper(String tokenPrefix, String secretKey, String tokenHeader) {
        this.tokenPrefix = tokenPrefix;
        this.secretKey = secretKey;
        this.tokenHeader = tokenHeader;
    }

    public String getTokenHeader() {
        return tokenHeader;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public SystemUserDto parseToken(String token) {
        try {
            final String decodedToken = JWT.require(HMAC512(secretKey.getBytes()))
                    .build()
                    .verify(token.substring(tokenPrefix.length() + 1))
                    .getSubject();
            return new ObjectMapper().readValue(decodedToken, SystemUserDto.class);
        } catch (Exception exc) {
            return null;
        }
    }

    public String generateToken(Authentication authentication) {
        final User user = ((User) authentication.getPrincipal());
        final StringBuilder subjectBuilder = new StringBuilder();
        subjectBuilder
                .append("{\"username\":\"").append(user.getUsername())
                .append("\", \"password\":\"").append(authentication.getCredentials().toString())
                .append("\"");
        if (!user.getAuthorities().isEmpty()) {
            subjectBuilder
                    .append(", \"role\" : \"").append(user.getAuthorities().toArray()[0])
                    .append("\"");
        }
        subjectBuilder.append("}");
        return JWT.create()
                .withSubject(subjectBuilder.toString())
                .sign(HMAC512(secretKey.getBytes()));
    }
}
