package com.senlainc.gitcourses.javatraining.petushokvaliantsin.configuration.filter;

import com.senlainc.gitcourses.javatraining.petushokvaliantsin.dto.SystemUserDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AuthorizationFilter extends BasicAuthenticationFilter {

    private final String tokenHeader;
    private final TokenMapper tokenMapper;

    public AuthorizationFilter(AuthenticationManager authenticationManager, String tokenHeader, TokenMapper tokenMapper) {
        super(authenticationManager);
        this.tokenHeader = tokenHeader;
        this.tokenMapper = tokenMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        final String token = request.getHeader(tokenHeader);
        if (token == null || token.isEmpty()) {
            chain.doFilter(request, response);
            return;
        }
        final UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(request);
        if (authenticationToken != null) {
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        final String token = request.getHeader(tokenHeader);
        if (token != null) {
            final SystemUserDto userDto = tokenMapper.parseToken(token);
            final List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
//            grantedAuthorities.add(new SimpleGrantedAuthority(userDto.getRole().name()));
            return new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword(), Collections.emptyList());
        }
        return null;
    }
}
