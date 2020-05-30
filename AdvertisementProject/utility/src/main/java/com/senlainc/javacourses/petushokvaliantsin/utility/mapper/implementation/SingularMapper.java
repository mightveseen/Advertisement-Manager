package com.senlainc.javacourses.petushokvaliantsin.utility.mapper.implementation;

import com.senlainc.javacourses.petushokvaliantsin.utility.exception.IncorrectCastException;
import com.senlainc.javacourses.petushokvaliantsin.utility.mapper.ISingularMapper;
import com.senlainc.javacourses.petushokvaliantsin.utility.mapper.annotation.SingularModel;
import org.springframework.stereotype.Component;

import javax.persistence.metamodel.SingularAttribute;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;

@Component
public class SingularMapper implements ISingularMapper {

    private final HashMap<String, Field> fields;

    public SingularMapper() {
        this.fields = new HashMap<>();
    }

    public void setClass(Class<?> clazz) {
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(SingularModel.class)) {
                final SingularModel annotation = method.getAnnotation(SingularModel.class);
                Arrays.asList(annotation.metamodels()).forEach(i -> {
                    for (Field field : i.getDeclaredFields()) {
                        if (field.getType().equals(SingularAttribute.class)) {
                            fields.put(field.getName().toLowerCase(), field);
                        }
                    }
                });
            }
        }
    }

    public <E, F> SingularAttribute<E, F> getSingularAttribute(String parameter) {
        try {
            return (SingularAttribute) fields.get(parameter.toLowerCase()).get(null);
        } catch (NullPointerException | IllegalAccessException e) {
            throw new IncorrectCastException("Chosen parameter [" + parameter + "] does not match any field");
        }
    }

}
