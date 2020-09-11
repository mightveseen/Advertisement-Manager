package com.senlainc.javacourses.petushokvaliantsin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Initializator {

    public static void main(String[] args) {
        ApplicationContext ff = SpringApplication.run(Initializator.class, args);
        for (String beanDefinitionName : ff.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }

    }
}
