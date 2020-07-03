package com.senlainc.javacourses.petushokvaliantsin.configuration.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senlainc.javacourses.petushokvaliantsin.configuration.exceptionhandler.ExceptionTemplate;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException {
        final ExceptionTemplate response = ExceptionTemplate.of(EnumException.PERMISSION_EXCEPTION.getMessage());
        final OutputStream out = httpServletResponse.getOutputStream();
        final ObjectMapper mapper = new ObjectMapper();
        httpServletResponse.setStatus(403);
        mapper.writeValue(out, response);
        out.flush();
    }
}
