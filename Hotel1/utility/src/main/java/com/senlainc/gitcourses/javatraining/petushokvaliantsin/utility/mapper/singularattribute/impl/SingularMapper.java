package com.senlainc.gitcourses.javatraining.petushokvaliantsin.utility.mapper.singularattribute.impl;

import com.senlainc.gitcourses.javatraining.petushokvaliantsin.utility.exception.IncorrectDataException;
import com.senlainc.gitcourses.javatraining.petushokvaliantsin.utility.mapper.singularattribute.ISingularMapper;
import com.senlainc.gitcourses.javatraining.petushokvaliantsin.utility.mapper.singularattribute.annotations.SingularClasses;
import org.springframework.stereotype.Component;

import javax.persistence.metamodel.SingularAttribute;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;

@Component
public class SingularMapper implements ISingularMapper {

    private final HashMap<String, Field> fields;
    private SingularClasses annotation;

    public SingularMapper() {
        fields = new HashMap<>();
    }

    public void setMethod(Method method) {
        this.annotation = method.getAnnotation(SingularClasses.class);
    }

    public <T, E> SingularAttribute<T, E> getSingularAttribute(String parameter) {
        Arrays.asList(annotation.metaModels()).forEach(i -> {
            for (Field field : i.getDeclaredFields()) {
                if (field.getName().equalsIgnoreCase(parameter) && field.getType().equals(SingularAttribute.class)) {
                    fields.put(parameter, field);
                }
            }
        });
        try {
            return (SingularAttribute) fields.get(parameter).get(null);
        } catch (IllegalAccessException e) {
            throw new IncorrectDataException("Chosen parameter [" + parameter + "] does not match any field", e);
        }
    }
}
