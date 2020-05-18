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

    public TokenMapper(String tokenPrefix, String secretKey) {
        this.tokenPrefix = tokenPrefix;
        this.secretKey = secretKey;
    }

    public SystemUserDto parseToken(String token) {
        try {
            if (tokenPrefix != null) {
                token = token.replace(tokenPrefix, "");
            }
            final String decodedToken = JWT.require(HMAC512(secretKey.getBytes()))
                    .build()
                    .verify(token)
                    .getSubject();
            return new ObjectMapper().readValue(decodedToken, SystemUserDto.class);
        } catch (Exception exc) {
            return null;
        }
    }

    public String generateToken(Authentication authentication) {
        final User user = ((User) authentication.getPrincipal());
        final String userName = user.getUsername();
        final String password = authentication.getCredentials().toString();
        final StringBuilder subjectBuilder = new StringBuilder();
        subjectBuilder.append("{\"username\":\"").append(userName)
                .append("\", \"password\":\"").append(password).append("\"");
        if (user.getAuthorities().isEmpty()) {
            subjectBuilder.append(", \"role\" : \"").append(user.getAuthorities().toArray()[0]).append("\"");
        }
        subjectBuilder.append("}");
        String token = JWT.create()
                .withSubject(subjectBuilder.toString())
                .sign(HMAC512(secretKey.getBytes()));
        if (tokenPrefix != null) {
            token = tokenPrefix + token;
        }
        return token;
    }
}
