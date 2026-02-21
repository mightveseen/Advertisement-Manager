package com.senlainc.javacourses.petushokvaliantsin.utility.mapper.implementation;

import com.senlainc.javacourses.petushokvaliantsin.utility.exception.MappingException;
import com.senlainc.javacourses.petushokvaliantsin.utility.mapper.IDtoMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.RecordComponent;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DtoMapper implements IDtoMapper {

    private final ModelMapper modelMapper;

    public DtoMapper() {
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE)
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
    }

    @Override
    public <S, D> D map(S object, Class<D> clazz) {
        if (object == null) {
            throw new MappingException("Error while mapping object into class: [" + clazz + "]");
        }
        if (clazz.isRecord()) {
            return mapToRecord(object, clazz);
        }
        return modelMapper.map(object, clazz);
    }

    @Override
    public <S, D> List<D> mapAll(List<S> objects, Class<D> clazz) {
        if (objects == null) {
            throw new MappingException("Error while mapping objects into class: [" + clazz + "]");
        }
        return objects.stream().map(i -> map(i, clazz)).collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    private <S, D> D mapToRecord(S source, Class<D> recordClass) {
        RecordComponent[] components = recordClass.getRecordComponents();
        Object[] args = new Object[components.length];
        Class<?>[] types = new Class<?>[components.length];

        for (int i = 0; i < components.length; i++) {
            RecordComponent component = components[i];
            types[i] = component.getType();
            Object value = getSourceValue(source, component.getName());

            if (value == null) {
                args[i] = null;
            } else if (Collection.class.isAssignableFrom(component.getType()) && value instanceof Collection<?> coll) {
                args[i] = mapCollection(coll, component);
            } else if (component.getType().isRecord()) {
                args[i] = mapToRecord(value, (Class<Object>) component.getType());
            } else {
                args[i] = value;
            }
        }

        try {
            Constructor<D> canonical = recordClass.getDeclaredConstructor(types);
            canonical.setAccessible(true);
            return canonical.newInstance(args);
        } catch (Exception e) {
            throw new MappingException("Error mapping to record " + recordClass.getSimpleName() + ": " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private Object mapCollection(Collection<?> coll, RecordComponent component) {
        if (!coll.isEmpty()) {
            Type genericType = component.getGenericType();
            if (genericType instanceof ParameterizedType pt) {
                Type elemType = pt.getActualTypeArguments()[0];
                if (elemType instanceof Class<?> elemClass && elemClass.isRecord()) {
                    List<Object> mapped = coll.stream()
                            .map(e -> mapToRecord(e, (Class<Object>) elemClass))
                            .collect(Collectors.toList());
                    return Set.class.isAssignableFrom(component.getType())
                            ? new LinkedHashSet<>(mapped) : mapped;
                }
            }
        }
        if (Set.class.isAssignableFrom(component.getType())) {
            return coll instanceof Set ? coll : new LinkedHashSet<>(coll);
        }
        return coll instanceof List ? coll : new ArrayList<>(coll);
    }

    private Object getSourceValue(Object source, String componentName) {
        // 1. Try getXxx() getter with exact component name (Lombok @Getter style)
        Method getter = findMethod(source.getClass(),
                "get" + Character.toUpperCase(componentName.charAt(0)) + componentName.substring(1));
        if (getter != null) {
            return invoke(getter, source, componentName);
        }

        // 2. Try record-style xxx() accessor (exact name)
        Method accessor = findNoArgMethod(source.getClass(), componentName);
        if (accessor != null) {
            return invoke(accessor, source, componentName);
        }

        // 3. Try last camelCase token (handles: advertisementCategory -> category, enumRole -> role)
        String suffix = lastCamelToken(componentName);
        if (!suffix.equals(componentName)) {
            Method suffixGetter = findMethod(source.getClass(),
                    "get" + Character.toUpperCase(suffix.charAt(0)) + suffix.substring(1));
            if (suffixGetter != null) {
                return invoke(suffixGetter, source, componentName);
            }

            Method suffixAccessor = findNoArgMethod(source.getClass(), suffix);
            if (suffixAccessor != null) {
                return invoke(suffixAccessor, source, componentName);
            }
        }

        // 4. Direct field access with exact name
        Field field = findField(source.getClass(), componentName);
        if (field != null) {
            return readField(field, source, componentName);
        }

        // 5. Direct field access with suffix name
        if (!suffix.equals(componentName)) {
            Field suffixField = findField(source.getClass(), suffix);
            if (suffixField != null) {
                return readField(suffixField, source, componentName);
            }
        }

        return null;
    }

    private Object invoke(Method method, Object source, String componentName) {
        try {
            method.setAccessible(true);
            return method.invoke(source);
        } catch (Exception e) {
            throw new MappingException("Error invoking '" + method.getName() + "' for component '" + componentName + "': " + e.getMessage());
        }
    }

    private Object readField(Field field, Object source, String componentName) {
        try {
            field.setAccessible(true);
            return field.get(source);
        } catch (Exception e) {
            throw new MappingException("Error reading field '" + field.getName() + "' for component '" + componentName + "': " + e.getMessage());
        }
    }

    private String lastCamelToken(String name) {
        for (int i = name.length() - 1; i > 0; i--) {
            if (Character.isUpperCase(name.charAt(i))) {
                return Character.toLowerCase(name.charAt(i)) + name.substring(i + 1);
            }
        }
        return name;
    }

    private Method findMethod(Class<?> clazz, String name) {
        Class<?> current = clazz;
        while (current != null && current != Object.class) {
            for (Method m : current.getDeclaredMethods()) {
                if (m.getName().equals(name) && m.getParameterCount() == 0) {
                    return m;
                }
            }
            current = current.getSuperclass();
        }
        return null;
    }

    private Method findNoArgMethod(Class<?> clazz, String name) {
        return findMethod(clazz, name);
    }

    private Field findField(Class<?> clazz, String name) {
        Class<?> current = clazz;
        while (current != null && current != Object.class) {
            try {
                return current.getDeclaredField(name);
            } catch (NoSuchFieldException ignored) {
                current = current.getSuperclass();
            }
        }
        return null;
    }
}
