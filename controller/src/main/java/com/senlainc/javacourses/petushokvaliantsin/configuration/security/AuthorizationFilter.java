package com.senlainc.javacourses.petushokvaliantsin.configuration.security;

import com.senlainc.javacourses.petushokvaliantsin.configuration.security.mapper.TokenMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

public class AuthorizationFilter extends BasicAuthenticationFilter {

    private final TokenMapper tokenMapper;

    public AuthorizationFilter(AuthenticationManager authenticationManager, TokenMapper tokenMapper) {
        super(authenticationManager);
        this.tokenMapper = tokenMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        final String token = request.getHeader(tokenMapper.getTokenHeader());
        if (token == null || token.isEmpty()) {
            chain.doFilter(request, response);
            return;
        }
        final UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(token);
        if (authenticationToken == null) {
            chain.doFilter(request, response);
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        final UserDetails userDetails = tokenMapper.parseToken(token);
        if (userDetails == null) {
            return null;
        }
        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(),
                userDetails.getAuthorities());
    }
}
