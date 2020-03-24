package com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.configuration.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface ConfigProperty {
    String configName();

    String propertyName() default "";
}