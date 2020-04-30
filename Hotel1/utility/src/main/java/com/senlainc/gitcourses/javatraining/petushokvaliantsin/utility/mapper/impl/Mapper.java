package com.senlainc.gitcourses.javatraining.petushokvaliantsin.utility.mapper.impl;

import com.senlainc.gitcourses.javatraining.petushokvaliantsin.utility.mapper.IMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapper implements IMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public Mapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public <D, T> D map(T entity, Class<D> outClazz) {
        return modelMapper.map(entity, outClazz);
    }

    @Override
    public <D, T> List<D> mapAll(List<T> entities, Class<D> outClazz) {
        return entities.stream().map(i -> map(i, outClazz)).collect(Collectors.toList());
    }
}
