package com.senlainc.javacourses.petushokvaliantsin.configuration.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senlainc.javacourses.petushokvaliantsin.configuration.exceptionhandler.ExceptionTemplate;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.io.OutputStream;

public class CustomAuthenticationEntryPointHandler implements AuthenticationEntryPoint {

    private static final String AUTHENTICATION_EXCEPTION = "You are not authorized to view this page";

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        final ExceptionTemplate response = ExceptionTemplate.of(AUTHENTICATION_EXCEPTION);
        final OutputStream out = httpServletResponse.getOutputStream();
        final ObjectMapper mapper = new ObjectMapper();
        httpServletResponse.setStatus(401);
        mapper.writeValue(out, response);
        out.flush();
    }
}
