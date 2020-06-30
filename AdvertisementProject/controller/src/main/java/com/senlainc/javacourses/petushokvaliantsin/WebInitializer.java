package com.senlainc.javacourses.petushokvaliantsin;

import com.senlainc.javacourses.petushokvaliantsin.configuration.AppConfig;
import com.senlainc.javacourses.petushokvaliantsin.configuration.SecurityConfig;
import com.senlainc.javacourses.petushokvaliantsin.configuration.WebConfig;
import com.senlainc.javacourses.petushokvaliantsin.configuration.exceptionhandler.AppExceptionHandler;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{AppConfig.class, AppExceptionHandler.class, SecurityConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
