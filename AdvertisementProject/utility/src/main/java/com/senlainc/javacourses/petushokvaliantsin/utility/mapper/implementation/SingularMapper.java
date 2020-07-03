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
        clazz.forEach(c -> Arrays.stream(c.getDeclaredMethods()).filter(method -> method.isAnnotationPresent(SingularModel.class))
                .forEach(method -> Arrays.stream(method.getAnnotation(SingularModel.class).metamodels())
                        .forEach(t -> Arrays.stream(t.getDeclaredFields()).filter(type -> type.getType().equals(SingularAttribute.class))
                                .forEach(f -> fields.put(t.getSimpleName().substring(0, t.getSimpleName().length() - 1).toLowerCase() + "-"
                                        + f.getName().toLowerCase(), f)
                                )
                        )
                )
        );
    }

    public <T, E> SingularAttribute<T, E> getAttribute(String parameter) {
        try {
            return parameter.endsWith("default") ? null : SingularAttribute.class.cast(fields.get(parameter).get(null));
        } catch (NullPointerException | IllegalAccessException e) {
            throw new IncorrectCastException("Chosen parameter [" + parameter + "] does not match any field");
        }
    }
}
