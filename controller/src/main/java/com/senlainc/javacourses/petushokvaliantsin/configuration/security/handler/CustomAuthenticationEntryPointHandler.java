package com.senlainc.javacourses.petushokvaliantsin.configuration.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senlainc.javacourses.petushokvaliantsin.configuration.exceptionhandler.ExceptionTemplate;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
