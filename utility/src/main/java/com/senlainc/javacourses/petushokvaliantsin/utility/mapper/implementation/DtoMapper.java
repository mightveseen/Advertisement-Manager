package com.senlainc.javacourses.petushokvaliantsin.utility.mapper.implementation;

import com.senlainc.javacourses.petushokvaliantsin.utility.exception.MappingException;
import com.senlainc.javacourses.petushokvaliantsin.utility.mapper.IDtoMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DtoMapper implements IDtoMapper {

    private final ModelMapper modelMapper;

    public DtoMapper() {
        this.modelMapper = new ModelMapper();
    }

    public <S, D> D map(S object, Class<D> clazz) {
        if (object == null) {
            throw new MappingException("Error while mapping object into class: [" + clazz + "]");
        }
        return modelMapper.map(object, clazz);
    }

    public <S, D> List<D> mapAll(List<S> objects, Class<D> clazz) {
        if (objects == null) {
            throw new MappingException("Error while mapping objects into class: [" + clazz + "]");
        }
        return objects.stream().map(i -> map(i, clazz)).collect(Collectors.toList());
    }
}
