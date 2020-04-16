package com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface;

import com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.MenuController;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.configuration.BeanConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Start {

    public static void main(String[] args) {
        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);
        for (String name : applicationContext.getBeanDefinitionNames()) {
            System.out.println(name);
        }
        new MenuController().run();
    }
}
