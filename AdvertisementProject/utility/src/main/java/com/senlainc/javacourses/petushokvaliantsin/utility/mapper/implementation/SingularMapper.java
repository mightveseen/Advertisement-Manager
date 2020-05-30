package com.senlainc.javacourses.petushokvaliantsin.utility.mapper.implementation;

import com.senlainc.javacourses.petushokvaliantsin.utility.exception.IncorrectCastException;
import com.senlainc.javacourses.petushokvaliantsin.utility.mapper.ISingularMapper;
import com.senlainc.javacourses.petushokvaliantsin.utility.mapper.annotation.SingularClass;
import com.senlainc.javacourses.petushokvaliantsin.utility.mapper.annotation.SingularModel;
import org.reflections.Reflections;

import javax.persistence.metamodel.SingularAttribute;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class SingularMapper implements ISingularMapper {

    private final HashMap<String, Field> fields;

    public SingularMapper(String packageScan) {
        this.fields = new HashMap<>();
        setClass(new Reflections(packageScan).getTypesAnnotatedWith(SingularClass.class));
    }

    private void setClass(Set<Class<?>> clazz) {
        clazz.forEach(i -> Arrays.stream(i.getDeclaredMethods()).forEach(j -> {
            if (j.isAnnotationPresent(SingularModel.class)) {
                Arrays.stream(j.getAnnotation(SingularModel.class).metamodels()).forEach(f -> {
                    for (Field field : f.getDeclaredFields()) {
                        if (field.getType().equals(SingularAttribute.class)) {
                            fields.put(field.getName().toLowerCase(), field);
                        }
                    }
                });
            }
        }));
    }

    public SingularAttribute getSingularAttribute(String parameter) {
        try {
            return SingularAttribute.class.cast(fields.get(parameter.toLowerCase()).get(null));
        } catch (NullPointerException | IllegalAccessException e) {
            throw new IncorrectCastException("Chosen parameter [" + parameter + "] does not match any field");
        }
    }

}
