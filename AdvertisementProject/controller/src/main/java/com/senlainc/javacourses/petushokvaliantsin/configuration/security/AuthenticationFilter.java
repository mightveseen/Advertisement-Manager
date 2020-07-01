package com.senlainc.javacourses.petushokvaliantsin.configuration.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senlainc.javacourses.petushokvaliantsin.configuration.security.mapper.TokenMapper;
import com.senlainc.javacourses.petushokvaliantsin.dto.user.UserCredDto;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.EntityNotExistException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final TokenMapper tokenMapper;

    public AuthenticationFilter(AuthenticationManager authenticationManager, TokenMapper tokenMapper) {
        this.tokenMapper = tokenMapper;
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
        final String token = tokenMapper.generateToken(authResult);
        response.setHeader(tokenMapper.getTokenHeader(), token);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        try {
            final StringBuilder requestBody = new StringBuilder();
            request.getReader().lines().collect(Collectors.toList()).forEach(requestBody::append);
            final UserCredDto userDto = new ObjectMapper().readValue(requestBody.toString(), UserCredDto.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
        } catch (IOException e) {
            throw new EntityNotExistException(e.getMessage());
        }
    }
}
